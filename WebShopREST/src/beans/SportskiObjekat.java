package beans;

import java.awt.Image;
import java.util.List;

public class SportskiObjekat {

	private String ime;
	private String tipObjekta;
	private List<String> sadrzajObjekta;
	private String status;
	private Lokacija lokacija;
	private Image logoObjekta;
	private double prosecnaOcena;
	private String radnoVreme;
	
	
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
	public Image getLogoObjekta() {
		return logoObjekta;
	}
	public void setLogoObjekta(Image logoObjekta) {
		this.logoObjekta = logoObjekta;
	}
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	public String getRadnoVreme() {
		return radnoVreme;
	}
	public void setRadnoVreme(String radnoVreme) {
		this.radnoVreme = radnoVreme;
	}
	
	
}
