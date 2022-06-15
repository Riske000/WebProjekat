package beans;

public class TipKupca {

	private String imeTipa;
	private double popust;
	private double potrebniPoeni;
	
	public TipKupca() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public TipKupca(String imeTipa, double popust, double potrebniPoeni) {
		super();
		this.imeTipa = imeTipa;
		this.popust = popust;
		this.potrebniPoeni = potrebniPoeni;
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
	
	
	
	
}
