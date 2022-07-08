package beans;

import java.time.LocalDateTime;

import utils.DateHelper;
import utils.DateTimeHelper;

public class IstorijaTreninga {

	private int intId;
	private LocalDateTime datumVremePrijave;
	private Trening trening;
	private Korisnik kupac;
	private Korisnik trener;		//ako je trener -1 onda datumVremePrijave oznacava kada se korisnik ulogovao, ako nije -1 onda oznacava kada je trening zakazan
	//a tada kupac treba da bude -1

	public IstorijaTreninga() {
		super();
	}

	public IstorijaTreninga(int intId, LocalDateTime datumVremePrijave, Trening trening, Korisnik kupac,
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

	public String convertToString() {
		return intId + ";" + DateTimeHelper.dateTimeToString(datumVremePrijave) + ";" + trening.getIntId() + ";"
				+ kupac.getIntId() + ";" + ((trener == null) ? -1 : trener.getIntId());
	}

}
