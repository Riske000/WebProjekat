package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import beans.Clanarina;
import beans.IstorijaTreninga;
import beans.Korisnik;
import beans.SportskiObjekat;
import beans.TipKupca;
import utils.DateHelper;
import utils.ImeTipa;
import utils.Uloge;

public class KorisnikDAO {

	private static KorisnikDAO korisnikInstance = null;
	private static String contextPath = "";

	public HashMap<Integer, Korisnik> korisnici = new HashMap<Integer, Korisnik>();

	private KorisnikDAO() {

	}

	private KorisnikDAO(String contextPath) {
		loadKorisnici(contextPath);
	}

	public static KorisnikDAO getInstance() {
		if (korisnikInstance == null) {
			korisnikInstance = new KorisnikDAO();
		}

		return korisnikInstance;
	}

	public Collection<Korisnik> findAll() {
		return korisnici.values();
	}

	public Korisnik save(Korisnik korisnik) {
		if (korisnik.getUloga() == Uloge.KUPAC) {
			TipKupca type = new TipKupca(0);
			type.setImeTipa(ImeTipa.BRONZANI);
			type.setPopust(0);
			type.setPotrebniPoeni(0);
			type = TipKupcaDAO.getInstance().save(type);
			korisnik.setTipKupca(type);
		}
		Integer maxId = -1;
		for (int id : korisnici.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		korisnik.setIntId(maxId);
		korisnici.put(korisnik.getIntId(), korisnik);
		sacuvajKorisnike();
		return korisnik;
	}

	public Korisnik find(int intId) {
		return korisnici.containsKey(intId) ? korisnici.get(intId) : null;
	}

	public boolean postojiKorisnickoIme(String korisnickoIme) {
		for (int intId : korisnici.keySet()) {
			Korisnik korisnik = korisnici.get(intId);
			if (korisnik.getKorisnickoIme().equals(korisnickoIme)) {
				return true;
			}
		}
		return false;
	}

	public Korisnik checkKorisnickoImeSifra(String korisnickoIme, String sifra) {
		Korisnik pronadjen = null;
		for (int intId : korisnici.keySet()) {
			Korisnik korisnik = korisnici.get(intId);
			if (korisnik.getKorisnickoIme().equals(korisnickoIme)) {
				pronadjen = korisnik;
			}
		}
		if (pronadjen != null) {
			if (!pronadjen.getSifra().equals(sifra)) {
				pronadjen = null;
			}
		}
		return pronadjen;
	}

	public Korisnik update(Korisnik korisnik) {

		if (korisnik.getSportskiObjekat() != null) { //izmeni ovo
			int id = korisnik.getSportskiObjekat().getIntId();
			Collection<SportskiObjekat> sportskiObjekti = SportskiObjekatDAO.getInstance().findAll();
			ArrayList<SportskiObjekat> sportskiObjekti1 = new ArrayList<SportskiObjekat>();
			for (SportskiObjekat sp : sportskiObjekti) {
				sportskiObjekti1.add(sp);
			}
			korisnik.setSportskiObjekat(sportskiObjekti1.get(sportskiObjekti1.size() - 1));
		}
		if (korisnik.getClanarina() != null) {
			int id = korisnik.getClanarina().getIntId();
			Clanarina clanarina = ClanarinaDAO.getInstance().findClanarina(id);
			korisnik.setClanarina(clanarina);
		}
		korisnici.put(korisnik.getIntId(), korisnik);
		sacuvajKorisnike();
		return korisnik;
	}

	public void delete(int id) {
		this.korisnici.remove(id);
	}

	public void loadKorisnici(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/files/korisnici.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, korisnickoIme = "", sifra = "", ime = "", prezime = "", pol = "", uloga = "";
			LocalDate datumRodjenja = LocalDate.now();
			int intId = -1;
			double brojSakupljenihPoena = -1;

			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					List<IstorijaTreninga> istorijaTreninga = new ArrayList<IstorijaTreninga>();
					Clanarina clanarina = null;
					List<SportskiObjekat> poseceniObjekti = new ArrayList<SportskiObjekat>();
					TipKupca tipKupca = null;
					SportskiObjekat sportskiObjekat = null;

					intId = Integer.parseInt(st.nextToken().trim());
					korisnickoIme = st.nextToken().trim();
					sifra = st.nextToken().trim();
					ime = st.nextToken().trim();
					prezime = st.nextToken().trim();
					pol = st.nextToken().trim();
					datumRodjenja = DateHelper.stringToDate(st.nextToken().trim());
					uloga = st.nextToken().trim();
					// istorija trening ucitavanje

					int clanarinaId = Integer.parseInt(st.nextToken().trim());

					if (clanarinaId != -1) {
						clanarina = new Clanarina(clanarinaId);
					}

					// poseceni objekti ucitavanje
					brojSakupljenihPoena = Double.parseDouble(st.nextToken().trim());

					int tipKupcaId = Integer.parseInt(st.nextToken().trim());

					if (tipKupcaId != -1) {
						tipKupca = new TipKupca(tipKupcaId);
					}

					int sportskiObjekatId = Integer.parseInt(st.nextToken().trim());

					if (sportskiObjekatId != -1) {
						sportskiObjekat = new SportskiObjekat(sportskiObjekatId);
					}
					korisnici.put(intId,
							new Korisnik(intId, korisnickoIme, sifra, ime, prezime, pol, datumRodjenja, uloga,
									istorijaTreninga, clanarina, poseceniObjekti, brojSakupljenihPoena, tipKupca,
									sportskiObjekat));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}

	}

	public void sacuvajKorisnike() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/korisnici.txt");
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for (Korisnik korisnik : korisnici.values()) {
				out.write(korisnik.convertToString() + '\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}

	}

	public void connectKorisnikPoseceniObjekti(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/files/poseceniObjekti.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					int korisnikID = Integer.parseInt(st.nextToken().trim());
					int objekatID = Integer.parseInt(st.nextToken().trim());
					Korisnik korisnik = find(korisnikID);
					SportskiObjekat sportskiObjekat = SportskiObjekatDAO.getInstance().findObjekat(objekatID);

					korisnik.getPoseceniObjekti().add(sportskiObjekat);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}

	}

	public void connectKorisnikClanarina() {
		ArrayList<Clanarina> clanarine = new ArrayList<Clanarina>(ClanarinaDAO.getInstance().findAll());
		for (Korisnik korisnik : korisnici.values()) {
			if (korisnik.getClanarina() == null) {
				continue;
			}
			int idTrazeni = korisnik.getClanarina().getIntId();

			for (Clanarina clanarina : clanarine) {
				if (clanarina.getIntId() == idTrazeni) {
					korisnik.setClanarina(clanarina);
					clanarina.setKupac(korisnik);
					break;
				}
			}
		}
	}

	public void connectKorisnikTipKupca() {
		ArrayList<TipKupca> tipoviKorisnika = new ArrayList<TipKupca>(TipKupcaDAO.getInstance().findAll());
		for (Korisnik korisnik : korisnici.values()) {
			if (korisnik.getTipKupca() == null) {
				continue;
			}
			int idTrazeni = korisnik.getTipKupca().getIntId();

			for (TipKupca tipKupca : tipoviKorisnika) {
				if (tipKupca.getIntId() == idTrazeni) {
					korisnik.setTipKupca(tipKupca);
					break;
				}
			}
		}
	}

	public void connectKorisnikSportskiObjekat() {
		ArrayList<SportskiObjekat> sportskiObjekti = new ArrayList<SportskiObjekat>(
				SportskiObjekatDAO.getInstance().findAll());
		for (Korisnik korisnik : korisnici.values()) {
			if (korisnik.getSportskiObjekat() == null) {
				continue;
			}
			int idTrazeni = korisnik.getSportskiObjekat().getIntId();

			for (SportskiObjekat sportskiObjekat : sportskiObjekti) {
				if (sportskiObjekat.getIntId() == idTrazeni) {
					korisnik.setSportskiObjekat(sportskiObjekat);
					break;
				}
			}
		}
	}

	public ArrayList<Korisnik> search(String searchIme, String searchPrezime, String searchKorisnickoIme,
			String tipKorisnika) {
		ArrayList<Korisnik> pronadjeni = new ArrayList<Korisnik>();

		for (Korisnik k : korisnici.values()) {
			if (k.getKorisnickoIme().toLowerCase().contains(searchKorisnickoIme.toLowerCase())) {
				if (k.getIme().toLowerCase().contains(searchIme.toLowerCase())) {
					if (k.getPrezime().toLowerCase().contains(searchPrezime.toLowerCase())) {
						if (k.getUloga().contains(tipKorisnika)) {
							pronadjeni.add(k);
						}
					}
				}
			}
		}

		return pronadjeni;
	}

	public ArrayList<Korisnik> getKupciZaSportskiObjekat(int idSportskogObjekta) {
		ArrayList<Korisnik> kupci = new ArrayList<Korisnik>();
		for (Korisnik korisnik : korisnici.values()) {
			for (SportskiObjekat sportskiObjekat : korisnik.getPoseceniObjekti()) {
				if (sportskiObjekat.getIntId() == idSportskogObjekta) {
					kupci.add(korisnik);
					break;
				}
			}
		}
		return kupci;
	}

	public ArrayList<Korisnik> getSlobodniMenadzeri() {
		ArrayList<Korisnik> freeManagers = new ArrayList<Korisnik>();

		for (Korisnik korisnik : korisnici.values()) {
			if (korisnik.getUloga().equals(Uloge.MENADZER)) {
				if (korisnik.getSportskiObjekat() == null) {
					freeManagers.add(korisnik);
				}
			}
		}
		return freeManagers;

	}

	public boolean cekirajSe(int intId) {
		for (Korisnik korisnik : korisnici.values()) {
			if (korisnik.getIntId() == intId) {
				if (LocalDate.now().isBefore(korisnik.getClanarina().getKrajnjiDatumVazenja())
						&& korisnik.getClanarina().getBrojTermina() > 0) {
					ClanarinaDAO.getInstance().smanjiBrojTermina(korisnik.getClanarina().getIntId());
					return true;
				}
			}
		}
		return false;
	}
	
	public Collection<Korisnik> getTreneri(){
		ArrayList<Korisnik> treneri = new ArrayList<Korisnik>();
		for(Korisnik k : korisnici.values()) {
			if(k.getUloga().equals("trener")) {
				treneri.add(k);
			}
		}
		return treneri;
	}

	public void podesiImeTipaKupca() {
		for(Korisnik k : korisnici.values()) {
			double poeni = k.getTipKupca().getPotrebniPoeni();
			if(poeni < 500) {
				k.getTipKupca().setImeTipa(ImeTipa.BRONZANI);
				k.getTipKupca().setPopust(2);
			}else if(poeni < 1000) {
				k.getTipKupca().setImeTipa(ImeTipa.SREBRNI);
				k.getTipKupca().setPopust(5);
			}else {
				k.getTipKupca().setImeTipa(ImeTipa.ZLATNI);
				k.getTipKupca().setPopust(10);
			}
		}
	}
}
