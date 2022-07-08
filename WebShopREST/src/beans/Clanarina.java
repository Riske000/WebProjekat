package beans;

import java.time.LocalDate;
import java.util.Arrays;

import utils.DateHelper;

public class Clanarina {

	private int intId;
	private String id;
	private String tipClanarine;
	private LocalDate datumPlacanja;
	private LocalDate pocetniDatumVazenja;
	private LocalDate krajnjiDatumVazenja;
	private double punaCena;
	private Korisnik kupac;
	private String status;
	private int brojTermina;
	
	
	
	public Clanarina() {
		super();
	}
	
	
	
	public Clanarina(int intId) {
		super();
		this.intId = intId;
	}

	

	public Clanarina(int intId, String id, String tipClanarine, LocalDate datumPlacanja, LocalDate pocetniDatumVazenja,
			LocalDate krajnjiDatumVazenja, double punaCena, Korisnik kupac, String status, int brojTermina) {
		super();
		this.intId = intId;
		this.id = id;
		this.tipClanarine = tipClanarine;
		this.datumPlacanja = datumPlacanja;
		this.pocetniDatumVazenja = pocetniDatumVazenja;
		this.krajnjiDatumVazenja = krajnjiDatumVazenja;
		this.punaCena = punaCena;
		this.kupac = kupac;
		this.status = status;
		this.brojTermina = brojTermina;
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
	public LocalDate getDatumPlacanja() {
		return datumPlacanja;
	}
	public void setDatumPlacanja(String datumPlacanja) {
		if(datumPlacanja == null || datumPlacanja.equals("")) {
			return;
		}
		this.datumPlacanja = DateHelper.stringToDate(datumPlacanja);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getPocetniDatumVazenja() {
		return datumPlacanja;
	}
	
	public void setPocetniDatumVazenja(String pocetniDatumVazenja) {
		if(pocetniDatumVazenja == null || pocetniDatumVazenja.equals("")) {
			return;
		}
		this.pocetniDatumVazenja = DateHelper.stringToDate(pocetniDatumVazenja);
	}
	
	public LocalDate getKrajnjiDatumVazenja() {
		return datumPlacanja;
	}
	
	public void setKrajnjiDatumVazenja(String krajnjiDatumVazenja) {
		if(krajnjiDatumVazenja == null || krajnjiDatumVazenja.equals("")) {
			return;
		}
		this.krajnjiDatumVazenja = DateHelper.stringToDate(krajnjiDatumVazenja);
	}
	
	public double getPunaCena() {
		return punaCena;
	}
	public void setPunaCena(double punaCena) {
		this.punaCena = punaCena;
	}
	public Korisnik getKupac() {
		return kupac;
	}
	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
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
	
	public String convertToString() {
		return intId + ";" + id + ";" + tipClanarine + ";" + DateHelper.dateToString(datumPlacanja) + ";" + DateHelper.dateToString(pocetniDatumVazenja) + ";" + DateHelper.dateToString(krajnjiDatumVazenja) + ";" + punaCena + ";" 
				+ kupac.getIntId() + ";" + status + ";" + brojTermina;
	}
	
}
