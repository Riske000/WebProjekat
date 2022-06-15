package beans;

public class IstorijaTreninga {

	private String datumVremePrijave;
	private Trening trening;
	private Korisnik kupac;
	private Korisnik trener;
	
	
	public String getDatumVremePrijave() {
		return datumVremePrijave;
	}
	public void setDatumVremePrijave(String datumVremePrijave) {
		this.datumVremePrijave = datumVremePrijave;
	}
	public Trening getTrening() {
		return trening;
	}
	public void setTrening(Trening trening) {
		this.trening = trening;
	}
	public Korisnik getKupac() {
		return kupac;
	}
	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}
	public Korisnik getTrener() {
		return trener;
	}
	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}
	
	
}
