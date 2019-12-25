package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.Henkilo;
import model.dao.HenkiloDAO;





@WebServlet("/henkilot")
public class HenkiloRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public HenkiloRestServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HenkiloDAO henkilodao = new HenkiloDAO();
			ArrayList<Henkilo>henkilot = henkilodao.findAll();
			
			// Java-kieli JSON dataksi
			String strJSON = new JSONObject().put("henkilot", henkilot).toString();
			
			response.setContentType("application/json");
			response.getWriter().println(strJSON);
		} catch (Exception e) {

			e.printStackTrace();
			throw new IOException("Pyynnön käsittelyssä tapahtui virhe");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Selaimelta tulleen JSON-muotoisen henkil�n deserialisointi Java-kieliseksi
		// Henkil�-luokan olioksi
		try {
			// Saadaan JSON-tiedot request-oliolta
			BufferedReader reader = request.getReader();
			StringBuffer strBuffer = new StringBuffer();
			String line = reader.readLine();
			while (line != null) {
				strBuffer.append(line);
				line = reader.readLine(); // next line
			}
			System.out.println(strBuffer.toString());

			JSONObject henkiloJsonObject = new JSONObject(strBuffer.toString());
			System.out.println(henkiloJsonObject);
			
			// Luodaan uusi Henkilo-luokan olio JSON-datan perusteella
			Henkilo henkilo = new Henkilo();
			henkilo.setNimi(henkiloJsonObject.getString("nimi"));
			henkilo.setOsoite(henkiloJsonObject.getString("osoite"));
			henkilo.setPuhnro(henkiloJsonObject.getString("puhnro"));
			System.out.println(henkilo);

			// Luodaan henkilodao
			HenkiloDAO henkilodao = new HenkiloDAO();
			// Lis�t��n henkil�n tiedot tietokantaan
			henkilodao.addHenkilo(henkilo);

			response.setContentType("application/json");
			// selaimeen l�hetet��n tietokantaan lis�tty henkilo JSON-muodossa
			response.getWriter().println(henkiloJsonObject); // ei viel� palauta hl�n id-arvoa

		} catch (JSONException e) {
			e.printStackTrace();
			throw new IOException("JSON-datan jäsentämisessä tapahtui virhe.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException("Tietokantakäsittely tapahtui virhe.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Pyynnön käsittelyssä tapahtui virhe.");
		}
		
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// Haetaan lomakkeella sy�tetyn henkilon tiedot request-oliolta
			String idStr = request.getParameter("henkiloid");
				 System.out.println("Poistettavan henkil�n id: " + idStr);
			int henkiloId = new Integer(idStr);
			// Luodaan henkilodao
			HenkiloDAO henkilodao = new HenkiloDAO();
			// Poistetaan henkil�n tiedot tietokannasta
			henkilodao.removeHenkilo(henkiloId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException("Sovelluksessa tapahtui tietokantavirhe.");
		}

	}
	}

