package beans;


public class Trening {

	private String naziv;
	private String tipTreninga;
	private SportskiObjekat objekatGdePripada;
	private double trajanje;
	private Korisnik trener;
	private String opis;
	private String slika;
	
	
	
	public Trening() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Trening(String naziv, String tipTreninga, SportskiObjekat objekatGdePripada, double trajanje,
			Korisnik trener, String opis, String slika) {
		super();
		this.naziv = naziv;
		this.tipTreninga = tipTreninga;
		this.objekatGdePripada = objekatGdePripada;
		this.trajanje = trajanje;
		this.trener = trener;
		this.opis = opis;
		this.slika = slika;
	}



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
	public double getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(double trajanje) {
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
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	
	
}
