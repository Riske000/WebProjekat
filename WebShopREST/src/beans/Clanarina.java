package beans;

import java.time.LocalDate;
import java.util.Arrays;

public class Clanarina {

	private String ID;
	private String tipClanarine;
	private LocalDate datumPlacanja;
	private LocalDate pocetniDatumVazenja;
	private LocalDate krajnjiDatumVazenja;
	private double punaCena;
	private Korisnik kupac;
	private String status;
	private int brojTermina;
	
	
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
