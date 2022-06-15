package beans;

public class Lokacija {
	
	private double geografskaSirina;
	private double geografskaDuzina;
	private String adresa;
	private String ulica;
	private String broj;
	private String mesto;
	private String postanskiBroj;
	
	public Lokacija() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Lokacija(double geografskaSirina, double geografskaDuzina, String adresa, String ulica, String broj,
			String mesto, String postanskiBroj) {
		super();
		this.geografskaSirina = geografskaSirina;
		this.geografskaDuzina = geografskaDuzina;
		this.adresa = adresa;
		this.ulica = ulica;
		this.broj = broj;
		this.mesto = mesto;
		this.postanskiBroj = postanskiBroj;
	}



	public double getGeografskaSirina() {
		return geografskaSirina;
	}
	public void setGeografskaSirina(double geografskaSirina) {
		this.geografskaSirina = geografskaSirina;
	}
	public double getGeografskaDuzina() {
		return geografskaDuzina;
	}
	public void setGeografskaDuzina(double geografskaDuzina) {
		this.geografskaDuzina = geografskaDuzina;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getPostanskiBroj() {
		return postanskiBroj;
	}
	public void setPostanskiBroj(String postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}
	
	public String getAdress() {
		adresa = this.ulica + " " + this.broj + ", " + this.mesto + ", " + this.postanskiBroj;
		return adresa;
	}
	
	

	
	
	
	
	
	
}
