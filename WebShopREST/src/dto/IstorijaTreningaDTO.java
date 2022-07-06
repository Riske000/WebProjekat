package dto;

import java.time.LocalDateTime;

import beans.IstorijaTreninga;
import beans.Korisnik;
import beans.Trening;
import utils.DateTimeHelper;

public class IstorijaTreningaDTO {
	private int intId;
	private LocalDateTime datumVremePrijave;
	private TreningDTO trening;
	private Korisnik trener;
	
	public IstorijaTreningaDTO() {
		
	}
	
	public IstorijaTreningaDTO(IstorijaTreninga istorijaTreninga) {
		this.intId = istorijaTreninga.getIntId();
		this.datumVremePrijave = istorijaTreninga.getDatumVremePrijave();
		this.trening = new TreningDTO(istorijaTreninga.getTrening());
		this.trener = istorijaTreninga.getTrener();
	}

	public int getIntId() {
		return intId;
	}
	public void setIntId(int intId) {
		this.intId = intId;
	}
	public String getDatumVremePrijave() {
		return DateTimeHelper.dateTimeToString(datumVremePrijave);
	}
	public void setDatumVremePrijave(String datumVremePrijave) {
		this.datumVremePrijave = DateTimeHelper.stringToDateTime(datumVremePrijave);
	}
	public TreningDTO getTrening() {
		return trening;
	}
	public void setTrening(TreningDTO trening) {
		this.trening = trening;
	}
	public int getTrener() {
		return (trener==null)?-1:trener.getIntId();
	}
	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}
	
	
	
}
