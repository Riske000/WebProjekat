package beans;

import java.time.LocalDateTime;

public class IstorijaTreninga {

	private LocalDateTime datumVremePrijave;
	private Trening trening;
	private Korisnik kupac;
	private Korisnik trener;
	
	public IstorijaTreninga() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public IstorijaTreninga(LocalDateTime datumVremePrijave, Trening trening, Korisnik kupac, Korisnik trener) {
		super();
		this.datumVremePrijave = datumVremePrijave;
		this.trening = trening;
		this.kupac = kupac;
		this.trener = trener;
	}



	public LocalDateTime getDatumVremePrijave() {
		return datumVremePrijave;
	}
	public void setDatumVremePrijave(LocalDateTime datumVremePrijave) {
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
