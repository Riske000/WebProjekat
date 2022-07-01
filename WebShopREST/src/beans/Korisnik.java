package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.UniqueConstraint;

import utils.DateHelper;

public class Korisnik {

	private int intId;
	private String korisnickoIme;
	private String sifra;
	private String ime;
	private String prezime;
	private String pol;
	private LocalDate datumRodjenja;
	private String uloga;
	
	// trener
	private List<IstorijaTreninga> istorijaTreninga;

	// kupac
	private Clanarina clanarina;
	private List<SportskiObjekat> poseceniObjekti;
	private double brojSakupljenihPoena;
	private TipKupca tipKupca;
	// menadzer
	private SportskiObjekat sportskiObjekat;

	public Korisnik() {
		super();
		this.istorijaTreninga = new ArrayList<IstorijaTreninga>();
		this.poseceniObjekti = new ArrayList<SportskiObjekat>();
	}

	public Korisnik(int intId) {
		super();
		this.intId = intId;
	}

	public Korisnik(int intId, String korisnickoIme, String sifra, String ime, String prezime, String pol,
			LocalDate datumRodjenja, String uloga, List<IstorijaTreninga> istorijaTreninga, Clanarina clanarina,
			List<SportskiObjekat> poseceniObjekti, double brojSakupljenihPoena, TipKupca tipKupca,
			SportskiObjekat sportskiObjekat) {
		super();
		this.intId = intId;
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.istorijaTreninga = istorijaTreninga;
		this.clanarina = clanarina;
		this.poseceniObjekti = poseceniObjekti;
		this.brojSakupljenihPoena = brojSakupljenihPoena;
		this.tipKupca = tipKupca;
		this.sportskiObjekat = sportskiObjekat;
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

	public List<IstorijaTreninga> getIstorijaTreninga() {
		return istorijaTreninga;
	}

	public void setIstorijaTreninga(List<IstorijaTreninga> istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
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

	public String convertToString() {
		return intId + ";" + korisnickoIme + ";" + sifra + ";" + ime + ";" + prezime + ";" + pol + ";" + DateHelper.dateToString(datumRodjenja) + ";" 
				+ uloga + ";" + ((clanarina == null)?-1:clanarina.getIntId()) + ";" + brojSakupljenihPoena + ";" + ((tipKupca == null)?-1:tipKupca.getIntId()) + ";" + ((sportskiObjekat == null)?-1:sportskiObjekat.getIntId());
	}

}
