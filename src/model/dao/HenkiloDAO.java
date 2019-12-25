package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Henkilo;
import model.dao.DataAccessObject;


public class HenkiloDAO extends DataAccessObject {

	
	public ArrayList<Henkilo> findAll() {	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Henkilo> henkilot = new ArrayList<Henkilo>();
		Henkilo henkilo = null; 
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit pizza-taulusta
			String sqlSelect = "SELECT id, nimi, osoite, puhnro FROM henkilo;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// Lähetetään komento:
			rs = stmt.executeQuery();
			// Käydään tulostaulun rivit läpi ja luetaan readHenkilo()-metodilla:
			while (rs.next()) {
				henkilo = readHenkilo(rs);
				// lisätään henkilö listaan
				henkilot.add(henkilo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}
	
		return henkilot;
	}

	private Henkilo readHenkilo(ResultSet rs) {
		try {
			// Haetaan yhden henkilön tiedot kyselyn tulostaulun (ResultSet-tyyppinen rs-olion) aktiiviselta tietoriviltä
			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			String osoite = rs.getString("osoite");
			String puhnro = rs.getString("puhnro");
		
			//  Luodaan ja palautetaan uusi henkilo
			return new Henkilo(id, nimi, osoite, puhnro);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void addHenkilo(Henkilo henkilo) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
	

		try {
			// Luodaan yhteys
			connection = getConnection();
			//Luodaan uusi henkilo tietokantaan:
			String sqlInsert = "INSERT INTO henkilo(id, nimi, osoite, puhnro) VALUES (?, ?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, henkilo.getId());
			stmtInsert.setString(2, henkilo.getNimi());
			stmtInsert.setString(3, henkilo.getOsoite());
			stmtInsert.setString(4, henkilo.getPuhnro());
			stmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection); // Suljetaan statement ja yhteys
		}
	}
	public void removeHenkilo(int henkiloId) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtDelete = null;

		try {
			// Luodaan yhteys
			connection = getConnection();
			//Poistetaan henkilo tietokantasta:
			String sqlInsert = "DELETE FROM henkilo WHERE id = ?";
			stmtDelete = connection.prepareStatement(sqlInsert);
			stmtDelete.setInt(1, henkiloId);
			stmtDelete.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection); // Suljetaan statement ja yhteys
		}
	}
	}

