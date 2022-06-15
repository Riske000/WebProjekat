package beans;

public class Komentar {

	private Korisnik kupac;
	private SportskiObjekat sportskiObjekat;
	private String tekstKomentara;
	private int ocena;
	
	
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
	
	
}
