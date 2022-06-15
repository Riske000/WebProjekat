package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.UniqueConstraint;

public class Korisnik implements Serializable {
	
	private String korisnickoIme;
	private String sifra;
	private String ime;
	private String prezime;
	private String pol;
	private LocalDate datumRodjenja;
	private String uloga;
	
	//trener
	private List<IstorijaTreninga> istorijaTreninga;
	
	//kupac
	private Clanarina clanarina;
	private List<SportskiObjekat> poseceniObjekti;
	private double brojSakupljenihPoena;
	private TipKupca tipKupca;
	//menadzer
	private SportskiObjekat sportskiObjekat;
	

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



	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}



	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
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



	public String getTipKupca() {
		return tipKupca;
	}



	public void setTipKupca(String tipKupca) {
		this.tipKupca = tipKupca;
	}



	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}



	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}



	public Korisnik() {
		super();
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, firstName, gender, lastName, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
	}

}
