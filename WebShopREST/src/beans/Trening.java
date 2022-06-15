package beans;

import java.awt.Image;

public class Trening {

	private String naziv;
	private String tipTreninga;
	private SportskiObjekat objekatGdePripada;
	private String trajanje;
	private Korisnik trener;
	private String opis;
	private Image slika;
	
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getTipTreninga() {
		return tipTreninga;
	}
	public void setTipTreninga(String tipTreninga) {
		this.tipTreninga = tipTreninga;
	}
	public SportskiObjekat getObjekatGdePripada() {
		return objekatGdePripada;
	}
	public void setObjekatGdePripada(SportskiObjekat objekatGdePripada) {
		this.objekatGdePripada = objekatGdePripada;
	}
	public String getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}
	public Korisnik getTrener() {
		return trener;
	}
	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Image getSlika() {
		return slika;
	}
	public void setSlika(Image slika) {
		this.slika = slika;
	}
	
	
}
