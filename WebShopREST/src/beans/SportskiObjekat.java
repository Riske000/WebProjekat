package beans;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import utils.DateHelper;
import utils.TimeHelper;

public class SportskiObjekat {

	private int intId;
	private String ime;
	private String tipObjekta;
	private List<String> sadrzajObjekta;
	private String status;
	private Lokacija lokacija;
	private String logoObjekta;
	private double prosecnaOcena;
	private LocalTime pocetakRadnogVremena; // local time
	private LocalTime krajRadnogVremena;

	public SportskiObjekat() {
		super();
		this.sadrzajObjekta = new ArrayList<String>();
	}

	public SportskiObjekat(int id) {
		super();
		intId = id;
	}

	public SportskiObjekat(int intId, String ime, String tipObjekta, List<String> sadrzajObjekta, String status,
			Lokacija lokacija, String logoObjekta, double prosecnaOcena, LocalTime pocetakRadnogVremena,
			LocalTime krajRadnogVremena) {
		super();
		this.intId = intId;
		this.ime = ime;
		this.tipObjekta = tipObjekta;
		this.sadrzajObjekta = sadrzajObjekta;
		this.status = status;
		this.lokacija = lokacija;
		this.logoObjekta = logoObjekta;
		this.prosecnaOcena = prosecnaOcena;
		this.pocetakRadnogVremena = pocetakRadnogVremena;
		this.krajRadnogVremena = krajRadnogVremena;
	}

	public int getIntId() {
		return intId;
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getTipObjekta() {
		return tipObjekta;
	}

	public void setTipObjekta(String tipObjekta) {
		this.tipObjekta = tipObjekta;
	}

	public List<String> getSadrzajObjekta() {
		return sadrzajObjekta;
	}

	public void setSadrzajObjekta(List<String> sadrzajObjekta) {
		this.sadrzajObjekta = sadrzajObjekta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public String getLogoObjekta() {
		return logoObjekta;
	}

	public void setLogoObjekta(String logoObjekta) {
		this.logoObjekta = logoObjekta;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public LocalTime getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public LocalTime getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
	}
	
	public String convertToString() { //da li treba lista sadrzajObjekta
		return intId + ";" + ime + ";" + tipObjekta + ";" + status + ";" + lokacija.getIntId() + ";" + logoObjekta + ";" + prosecnaOcena + ";" 
				+ TimeHelper.timeToString(pocetakRadnogVremena) + ";" + TimeHelper.timeToString(krajRadnogVremena);
	}

}
