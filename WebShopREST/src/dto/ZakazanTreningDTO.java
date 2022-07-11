package dto;

import java.time.LocalDateTime;

import beans.Trening;
import beans.ZakazanTrening;
import utils.DateTimeHelper;

public class ZakazanTreningDTO {

	private int intID;
	private LocalDateTime terminTreninga;
	private String statusTreninga;
	private int kupacIntId;
	private int trenerIntId;
	private int objekatIntId;
	
	public ZakazanTreningDTO() {
		
	}
	
	public ZakazanTreningDTO(int intID) {
		this.intID = intID;
	}
	
	public ZakazanTreningDTO(ZakazanTrening zakazanTrening) {
		this.intID = zakazanTrening.getIntID();
		this.terminTreninga = DateTimeHelper.stringToDateTime(zakazanTrening.getTerminTreninga());
		this.statusTreninga = zakazanTrening.getStatusTreninga();
		this.kupacIntId = zakazanTrening.getKupac().getIntId();
		this.trenerIntId = zakazanTrening.getTrener().getIntId();
		this.objekatIntId = zakazanTrening.getObjekatGdePripada().getIntId();
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

	public int getKupacIntId() {
		return kupacIntId;
	}

	public void setKupacIntId(int kupacIntId) {
		this.kupacIntId = kupacIntId;
	}

	public int getTrenerIntId() {
		return trenerIntId;
	}

	public void setTrenerIntId(int trenerIntId) {
		this.trenerIntId = trenerIntId;
	}

	public int getObjekatIntId() {
		return objekatIntId;
	}

	public void setObjekatIntId(int objekatIntId) {
		this.objekatIntId = objekatIntId;
	}
	
	
	 
}
