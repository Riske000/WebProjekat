package dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import beans.Clanarina;
import beans.IstorijaTreninga;
import beans.Korisnik;
import beans.SportskiObjekat;
import beans.TipKupca;
import utils.DateHelper;

public class KorisnikDTO {
	private int intId;
	private String korisnickoIme;
	private String sifra;
	private String ime;
	private String prezime;
	private String pol;
	private LocalDate datumRodjenja;
	private String uloga;

	// kupac
	private Clanarina clanarina;
	private List<SportskiObjekat> poseceniObjekti;
	private double brojSakupljenihPoena;
	private TipKupca tipKupca;
	// menadzer
	private SportskiObjekat sportskiObjekat;

	public KorisnikDTO() {
		super();
		this.poseceniObjekti = new ArrayList<SportskiObjekat>();
	}

	public KorisnikDTO(Korisnik korisnik) {
		super();
		this.intId = korisnik.getIntId();
		this.korisnickoIme = korisnik.getKorisnickoIme();
		this.sifra = korisnik.getSifra();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.pol = korisnik.getPol();
		this.datumRodjenja = DateHelper.stringToDate(korisnik.getDatumRodjenja());
		this.uloga = korisnik.getUloga();
		this.clanarina = korisnik.getClanarina();
		this.poseceniObjekti = korisnik.getPoseceniObjekti();
		this.brojSakupljenihPoena = korisnik.getBrojSakupljenihPoena();
		this.tipKupca = korisnik.getTipKupca();
		this.sportskiObjekat = korisnik.getSportskiObjekat();
	}

	public int getIntId() {
		return intId;
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getDatumRodjenja() {
		return DateHelper.dateToString(datumRodjenja);
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = DateHelper.stringToDate(datumRodjenja);
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public Clanarina getClanarina() {
		return clanarina;
	}

	public void setClanarina(Clanarina clanarina) {
		this.clanarina = clanarina;
	}

	public List<SportskiObjekat> getPoseceniObjekti() {
		return poseceniObjekti;
	}

	public void setPoseceniObjekti(List<SportskiObjekat> poseceniObjekti) {
		this.poseceniObjekti = poseceniObjekti;
	}

	public double getBrojSakupljenihPoena() {
		return brojSakupljenihPoena;
	}

	public void setBrojSakupljenihPoena(double brojSakupljenihPoena) {
		this.brojSakupljenihPoena = brojSakupljenihPoena;
	}

	public TipKupca getTipKupca() {
		return tipKupca;
	}

	public void setTipKupca(TipKupca tipKupca) {
		this.tipKupca = tipKupca;
	}

	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
}
