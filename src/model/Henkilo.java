package model;

public class Henkilo {
	private int id;
	private String nimi;
	private String osoite;
	private String puhnro;
	
//	PARAMETRITON KONSTRUKTORI
	public Henkilo() {
		super();
	}

//	PARAMETRILLINEN KONSTRUKTORI
	
public Henkilo(int id, String nimi, String osoite, String puhnro) {
	super();
	this.id = id;
	this.nimi = nimi;
	this.osoite = osoite;
	this.puhnro = puhnro;
}
// GETIT
public int getId() {
	return id;
}

public String getNimi() {
	return nimi;
}

public String getOsoite() {
	return osoite;
}

public String getPuhnro() {
	return puhnro;
}

//	SETIT
public void setId(int id) {
	this.id = id;
}

public void setNimi(String nimi) {
	this.nimi = nimi;
}

public void setOsoite(String osoite) {
	this.osoite = osoite;
}

public void setPuhnro(String puhnro) {
	this.puhnro = puhnro;
}

//	TOSTRING
@Override
public String toString() {
	return "Henkilo [id=" + id + ", nimi=" + nimi + ", osoite=" + osoite + ", puhnro=" + puhnro + "]";
}
	

}	
	


