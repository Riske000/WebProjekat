package utils;

import java.util.HashMap;

import dao.ClanarinaDAO;
import dao.IstorijaTreningaDAO;
import dao.KomentarDAO;
import dao.KorisnikDAO;
import dao.LokacijaDAO;
import dao.SportskiObjekatDAO;
import dao.TipKupcaDAO;
import dao.TreningDAO;


public class PokretanjeProjekta {

	private static PokretanjeProjekta pokretanjeInstance = null;


	private PokretanjeProjekta(String contextPath) {
		ClanarinaDAO.getInstance().loadClanarine(contextPath);
		IstorijaTreningaDAO.getInstance().loadIstorijeTreninga(contextPath);
		KomentarDAO.getInstance().loadKomentari(contextPath);
		KorisnikDAO.getInstance().loadKorisnici(contextPath);
		LokacijaDAO.getInstance().loadLokacije(contextPath);
		SportskiObjekatDAO.getInstance().loadSportskiObjekti(contextPath);
		TipKupcaDAO.getInstance().loadTipoviKupca(contextPath);
		TreningDAO.getInstance().loadTreninzi(contextPath);
		
		
		KorisnikDAO.getInstance().connectKorisnikClanarina();
		KorisnikDAO.getInstance().connectKorisnikSportskiObjekat();
		KorisnikDAO.getInstance().connectKorisnikTipKupca();
		KorisnikDAO.getInstance().connectKorisnikPoseceniObjekti(contextPath);
		
		KomentarDAO.getInstance().connectKomentarSportskiObjekat();
		KomentarDAO.getInstance().connectKomentarKupac();
		
		SportskiObjekatDAO.getInstance().connectSportskiObjekatLokacija();
		
		TreningDAO.getInstance().connectTreningTrener();
		TreningDAO.getInstance().connectTreningSportskiObjekat();
		
		IstorijaTreningaDAO.getInstance().connectIstorijaTreningaKupac();
		IstorijaTreningaDAO.getInstance().connectIstorijaTreningaTrener();
		IstorijaTreningaDAO.getInstance().connectIstorijaTreningaTrening();
	}

	public static PokretanjeProjekta getInstance(String contextPath) {
		if (pokretanjeInstance == null) {
			pokretanjeInstance = new PokretanjeProjekta(contextPath);
		}

		return pokretanjeInstance;
	}
}
