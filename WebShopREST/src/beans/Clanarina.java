package beans;

import java.time.LocalDate;
import java.util.Arrays;

public class Clanarina {

	private int intId;
	private String ID;
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

	

	public Clanarina(int intId, String iD, String tipClanarine, LocalDate datumPlacanja, LocalDate pocetniDatumVazenja,
			LocalDate krajnjiDatumVazenja, double punaCena, Korisnik kupac, String status, int brojTermina) {
		super();
		this.intId = intId;
		ID = iD;
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
	public void setDatumPlacanja(LocalDate datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public LocalDate getPocetniDatumVazenja() {
		return pocetniDatumVazenja;
	}
	public void setPocetniDatumVazenja(LocalDate pocetniDatumVazenja) {
		this.pocetniDatumVazenja = pocetniDatumVazenja;
	}
	public LocalDate getKrajnjiDatumVazenja() {
		return krajnjiDatumVazenja;
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

	
}
