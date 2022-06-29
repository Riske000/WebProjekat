package beans;

import utils.DateHelper;

public class Komentar {

	private int intId;
	private Korisnik kupac;
	private SportskiObjekat sportskiObjekat;
	private String tekstKomentara;
	private int ocena;
	
	public Komentar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


	
	
	public Komentar(int intId, Korisnik kupac, SportskiObjekat sportskiObjekat, String tekstKomentara, int ocena) {
		super();
		this.intId = intId;
		this.kupac = kupac;
		this.sportskiObjekat = sportskiObjekat;
		this.tekstKomentara = tekstKomentara;
		this.ocena = ocena;
	}








	public int getIntId() {
		return intId;
	}



	public void setIntId(int intId) {
		this.intId = intId;
	}



	public Korisnik getKupac() {
		return kupac;
	}
	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}
	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}
	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
	public String getTekstKomentara() {
		return tekstKomentara;
	}
	public void setTekstKomentara(String tekstKomentara) {
		this.tekstKomentara = tekstKomentara;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	public String convertToString() {
		return intId + ";" + kupac.getIntId() + ";" + sportskiObjekat.getIntId() + ";" + tekstKomentara + ";" + ocena;
	}
	
}
