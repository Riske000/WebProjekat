package beans;

import java.time.LocalDateTime;

import utils.DateHelper;
import utils.DateTimeHelper;

public class ZakazanTrening {

	private int intID;
	private LocalDateTime terminTreninga;
	private String statusTreninga;
	private Korisnik kupac;
	private Korisnik trener;
	private SportskiObjekat objekatGdePripada;
	
	public ZakazanTrening() {
		
	}
	
	public ZakazanTrening(int intID) {
		this.intID = intID;
	}

	public ZakazanTrening(int intID, LocalDateTime terminTreninga, String statusTreninga, Korisnik kupac,
			Korisnik trener, SportskiObjekat objekatGdePripada) {
		super();
		this.intID = intID;
		this.terminTreninga = terminTreninga;
		this.statusTreninga = statusTreninga;
		this.kupac = kupac;
		this.trener = trener;
		this.objekatGdePripada = objekatGdePripada;
	}

	public int getIntID() {
		return intID;
	}

	public void setIntID(int intID) {
		this.intID = intID;
	}

	public String getTerminTreninga() {
		return DateTimeHelper.dateTimeToString(terminTreninga);
	}

	public void setTerminTreninga(String terminTreninga) {
		this.terminTreninga = DateTimeHelper.stringToDateTime(terminTreninga);
	}

	public String getStatusTreninga() {
		return statusTreninga;
	}

	public void setStatusTreninga(String statusTreninga) {
		this.statusTreninga = statusTreninga;
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

	public SportskiObjekat getObjekatGdePripada() {
		return objekatGdePripada;
	}

	public void setObjekatGdePripada(SportskiObjekat objekatGdePripada) {
		this.objekatGdePripada = objekatGdePripada;
	}
	
	public String convertToString() {
		return intID + ";" + DateTimeHelper.dateTimeToString(terminTreninga) + ";" + statusTreninga + ";" + kupac.getIntId() + ";" + trener.getIntId() + ";" + objekatGdePripada.getIntId();
	}
}
