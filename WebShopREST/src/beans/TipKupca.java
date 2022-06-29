package beans;

import utils.TimeHelper;

public class TipKupca {

	private int intId;
	private String imeTipa;
	private double popust;
	private double potrebniPoeni;
	
	public TipKupca() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public TipKupca(int intId) {
		super();
		this.intId = intId;
	}
	


	
	
	public TipKupca(int intId, String imeTipa, double popust, double potrebniPoeni) {
		super();
		this.intId = intId;
		this.imeTipa = imeTipa;
		this.popust = popust;
		this.potrebniPoeni = potrebniPoeni;
	}








	public int getIntId() {
		return intId;
	}



	public void setIntId(int intId) {
		this.intId = intId;
	}



	public String getImeTipa() {
		return imeTipa;
	}
	public void setImeTipa(String imeTipa) {
		this.imeTipa = imeTipa;
	}
	public double getPopust() {
		return popust;
	}
	public void setPopust(double popust) {
		this.popust = popust;
	}
	public double getPotrebniPoeni() {
		return potrebniPoeni;
	}
	public void setPotrebniPoeni(double potrebniPoeni) {
		this.potrebniPoeni = potrebniPoeni;
	}
	
	public String convertToString() { 
		return intId + ";" + imeTipa + ";" + popust + ";" + potrebniPoeni;
	}
}
