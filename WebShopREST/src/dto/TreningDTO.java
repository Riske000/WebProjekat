package dto;

import beans.SportskiObjekat;
import beans.Trening;

public class TreningDTO {
	private int intId;
	private String naziv;
	private String tipTreninga;
	private SportskiObjekat objekatGdePripada;
	private double trajanje;
	private String opis;
	private String slika;
	private String trener;
	
	public TreningDTO(Trening trening) {
		this.intId = trening.getIntId();
		this.naziv = trening.getNaziv();
		this.tipTreninga = trening.getTipTreninga();
		this.objekatGdePripada = trening.getObjekatGdePripada();
		this.trajanje = trening.getTrajanje();
		this.opis = trening.getOpis();
		this.slika = trening.getSlika();
		this.trener = (trening.getTrener()==null)?null:(trening.getTrener().getIme() + " " + trening.getTrener().getPrezime());
	}


	public TreningDTO(int intId, String naziv, String tipTreninga, SportskiObjekat objekatGdePripada,
			double trajanje, String opis, String slika) {
		super();
		this.intId = intId;
		this.naziv = naziv;
		this.tipTreninga = tipTreninga;
		this.objekatGdePripada = objekatGdePripada;
		this.trajanje = trajanje;
		this.opis = opis;
		this.slika = slika;
	}

	public TreningDTO(int intId) {
		this.intId = intId;
	}

	public TreningDTO() {
		super();
	}


	public int getIntId() {
		return intId;
	}


	public void setId(int intId) {
		this.intId = intId;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getTipTreninga() {
		return tipTreninga;
	}


	public void setTipTreninga(String tipTreninga) {
		this.tipTreninga = tipTreninga;
	}


	public SportskiObjekat getObjekatGdePripada() {
		return objekatGdePripada;
	}


	public void setObjekatGdePripada(SportskiObjekat objekatGdePripada) {
		this.objekatGdePripada = objekatGdePripada;
	}


	public double getTrajanje() {
		return trajanje;
	}


	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}


	public String getSlika() {
		return slika;
	}


	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getTrener() {
		return trener;
	}


	public void setTrener(String trener) {
		this.trener = trener;
	}

}
