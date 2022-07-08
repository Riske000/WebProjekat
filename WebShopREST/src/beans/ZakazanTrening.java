package beans;

import java.time.LocalDateTime;

import utils.DateTimeHelper;

public class ZakazanTrening {

	private int intID;
	private Trening trening;
	private LocalDateTime terminTreninga;
	private String statusTreninga;
	
	public ZakazanTrening() {
		
	}
	
	public ZakazanTrening(int intID) {
		this.intID = intID;
	}

	public ZakazanTrening(int intID, Trening trening, LocalDateTime terminTreninga, String statusTreninga) {
		super();
		this.intID = intID;
		this.trening = trening;
		this.terminTreninga = terminTreninga;
		this.statusTreninga = statusTreninga;
	}

	public int getIntId() {
		return intID;
	}

	public void setIntId(int intID) {
		this.intID = intID;
	}

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
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
	
	public String convertToString() {
		return intID + ";" + trening.getIntId() + ";" + DateTimeHelper.dateTimeToString(terminTreninga) + ";" + statusTreninga;
	}
	
}
