package dto;

import java.time.LocalDate;

import beans.Clanarina;
import beans.Korisnik;
import utils.DateHelper;

public class ClanarinaDTO {
	private int intId;
	private String id;
	private String tipClanarine;
	private LocalDate datumPlacanja;
	private LocalDate pocetniDatumVazenja;
	private LocalDate krajnjiDatumVazenja;
	private double punaCena;
	private String status;
	private int brojTermina;
	
	
	
	public ClanarinaDTO() {
		super();
	}
	

	public ClanarinaDTO(Clanarina clanarina) {
		super();
		this.intId = clanarina.getIntId();
		this.id = clanarina.getId();
		this.tipClanarine = clanarina.getTipClanarine();
		this.datumPlacanja = clanarina.getDatumPlacanja();
		this.pocetniDatumVazenja = clanarina.getPocetniDatumVazenja();
		this.krajnjiDatumVazenja = clanarina.getKrajnjiDatumVazenja();
		this.punaCena = clanarina.getPunaCena();
		this.status = clanarina.getStatus();
		this.brojTermina = clanarina.getBrojTermina();
	}







	public int getIntId() {
		return intId;
	}



	public void setIntId(int intId) {
		this.intId = intId;
	}



	public String getTipClanarine() {
		return tipClanarine;
	}
	public void setTipClanarine(String tipClanarine) {
		this.tipClanarine = tipClanarine;
	}
	public String getDatumPlacanja() {
		return DateHelper.dateToString(datumPlacanja);
	}
	public void setDatumPlacanja(String datumPlacanja) {
		this.datumPlacanja = DateHelper.stringToDate(datumPlacanja);
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPocetniDatumVazenja() {
		if(pocetniDatumVazenja == null) {
			return "";
		}
		return DateHelper.dateToString(pocetniDatumVazenja);
	}
	public void setPocetniDatumVazenja(LocalDate pocetniDatumVazenja) {
		this.pocetniDatumVazenja =pocetniDatumVazenja;
	}
	public String getKrajnjiDatumVazenja() {
		if(krajnjiDatumVazenja == null) {
			return "";
		}
		return DateHelper.dateToString(krajnjiDatumVazenja);
	}
	public void setKrajnjiDatumVazenja(LocalDate krajnjiDatumVazenja) {
		this.krajnjiDatumVazenja = krajnjiDatumVazenja;
	}
	public double getPunaCena() {
		return punaCena;
	}
	public void setPunaCena(double punaCena) {
		this.punaCena = punaCena;
	}
	
	public int getBrojTermina() {
		return brojTermina;
	}
	public void setBrojTermina(int brojTermina) {
		this.brojTermina = brojTermina;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
