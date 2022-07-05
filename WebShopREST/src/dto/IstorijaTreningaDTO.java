package dto;

import java.time.LocalDateTime;

import beans.IstorijaTreninga;
import beans.Korisnik;
import beans.Trening;

public class IstorijaTreningaDTO {
	private int intId;
	private LocalDateTime datumVremePrijave;
	private Trening trening;
	private Korisnik kupac;
	private Korisnik trener;
	
	public IstorijaTreningaDTO() {
		
	}
	
	public IstorijaTreningaDTO(IstorijaTreninga istorijaTreninga) {
		this.intId = istorijaTreninga.getIntId();
		this.datumVremePrijave = istorijaTreninga.getDatumVremePrijave();
		this.trening = istorijaTreninga.getTrening();
		this.kupac = istorijaTreninga.getKupac();
		this.trener = istorijaTreninga.getTrener();
	}
	
	public IstorijaTreningaDTO(int intId, LocalDateTime datumVremePrijave, Trening trening, Korisnik kupac,
			Korisnik trener) {
		super();
		this.intId = intId;
		this.datumVremePrijave = datumVremePrijave;
		this.trening = trening;
		this.kupac = kupac;
		this.trener = trener;
	}
	public int getIntId() {
		return intId;
	}
	public void setIntId(int intId) {
		this.intId = intId;
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
	public int getKupac() {
		return kupac.getIntId();
	}
	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}
	public int getTrener() {
		return (trener==null)?-1:trener.getIntId();
	}
	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}
	
	
	
}
