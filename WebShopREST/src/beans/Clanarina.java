package beans;

import java.util.Arrays;

public class Clanarina {

	char[] chars = new char[10];
	String iD = new String(chars);
	private String tipClanarine;
	private String datumPlacanja;
	private String datumVremeVazenja;
	private int punaCena;
	private Korisnik kupac;
	private int brojTermina;
	
	
	
	
	
	public String getiD() {
		return iD;
	}
	public void setiD(String iD) {
		this.iD = iD;
	}
	public String getTipClanarine() {
		return tipClanarine;
	}
	public void setTipClanarine(String tipClanarine) {
		this.tipClanarine = tipClanarine;
	}
	public String getDatumPlacanja() {
		return datumPlacanja;
	}
	public void setDatumPlacanja(String datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}
	public String getDatumVremeVazenja() {
		return datumVremeVazenja;
	}
	public void setDatumVremeVazenja(String datumVremeVazenja) {
		this.datumVremeVazenja = datumVremeVazenja;
	}
	public int getPunaCena() {
		return punaCena;
	}
	public void setPunaCena(int punaCena) {
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
	
	
}
