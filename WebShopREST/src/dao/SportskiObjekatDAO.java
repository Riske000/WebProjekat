package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import beans.Korisnik;
import beans.Lokacija;
import beans.SportskiObjekat;
import utils.TimeHelper;

public class SportskiObjekatDAO {

	private static SportskiObjekatDAO sportskiObjekatInstance = null;
	private static String contextPath = "";

	private HashMap<Integer, SportskiObjekat> sportskiObjekti = new HashMap<Integer, SportskiObjekat>();

	private SportskiObjekatDAO() {

	}

	private SportskiObjekatDAO(String contextPath) {
		loadSportskiObjekti(contextPath);
	}

	public static SportskiObjekatDAO getInstance() {
		if (sportskiObjekatInstance == null) {
			sportskiObjekatInstance = new SportskiObjekatDAO();
		}

		return sportskiObjekatInstance;
	}

	public Collection<SportskiObjekat> findAll() {
		return sportskiObjekti.values();
	}

	public SportskiObjekat findObjekat(int id) {
		return sportskiObjekti.containsKey(id) ? sportskiObjekti.get(id) : null;
	}

	public SportskiObjekat save(SportskiObjekat sportskiObjekat) {
		Integer maxId = -1;
		for (Integer id : sportskiObjekti.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		sportskiObjekat.setIntId(maxId);

		LokacijaDAO.getInstance().save(sportskiObjekat.getLokacija());
		if (sportskiObjekat.getLokacija() != null) {
			Collection<Lokacija> lokacije = LokacijaDAO.getInstance().findAll();
			ArrayList<Lokacija> lokacije1 = new ArrayList<Lokacija>();
			for (Lokacija l : lokacije) {
				lokacije1.add(l);
			}
			sportskiObjekat.setLokacija(lokacije1.get(lokacije1.size() - 1));
		}

		sportskiObjekti.put(sportskiObjekat.getIntId(), sportskiObjekat);
		sacuvajSportskeObjekte();
		return sportskiObjekat;
	}

	public SportskiObjekat update(SportskiObjekat sportskiObjekat) {
		sportskiObjekti.put(sportskiObjekat.getIntId(), sportskiObjekat);
		return sportskiObjekat;
	}

	public void delete(String id) {
		this.sportskiObjekti.remove(id);
	}

	public void loadSportskiObjekti(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;
		try {
			File file = new File(contextPath + "/files/sportskiobjekti.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, ime = "", tipObjekta = "", status = "", logoObjekta = "";
			List<String> sadrzajObjekta = new ArrayList<String>();
			double prosecnaOcena = 0;
			LocalTime pocetak = LocalTime.now();
			LocalTime kraj = LocalTime.now();
			int id = -1;
			Lokacija lokacija = new Lokacija();
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					ime = st.nextToken().trim();
					tipObjekta = st.nextToken().trim();
					// za sadrzaj
					status = st.nextToken().trim();

					lokacija = new Lokacija(Integer.parseInt(st.nextToken().trim()));
					logoObjekta = st.nextToken().trim();
					prosecnaOcena = Double.parseDouble(st.nextToken().trim());
					pocetak = TimeHelper.stringToTime(st.nextToken().trim());
					kraj = TimeHelper.stringToTime(st.nextToken().trim());
				}
				sportskiObjekti.put(id, new SportskiObjekat(id, ime, tipObjekta, sadrzajObjekta, status, lokacija,
						logoObjekta, prosecnaOcena, pocetak, kraj));

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

	public void sacuvajSportskeObjekte() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/sportskiObjekti.txt"); // proveri naziv fajla
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for (SportskiObjekat sportskiObjekat : sportskiObjekti.values()) {
				out.write(sportskiObjekat.convertToString() + '\n');
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

	public ArrayList<SportskiObjekat> search(String searchIme, String searchTip, String searchLokacija,
			String searchOcena, String daLiRadi, String filterStatus) {
		ArrayList<SportskiObjekat> pronadjeni = new ArrayList<SportskiObjekat>();

		for (SportskiObjekat sp : sportskiObjekti.values()) {
			if (sp.getIme().toLowerCase().contains(searchIme.toLowerCase())) {
				if (sp.getLokacija().celaAdress().toLowerCase().contains(searchLokacija.toLowerCase())) {
					if (sp.getTipObjekta().toLowerCase().contains(searchTip.toLowerCase())) {
						if (sp.getProsecnaOcena() >= Double.parseDouble(searchOcena)) {
							if (daLiRadi.equals("true")) {
								if (sp.getStatus().equals("radi")) {
									if (sp.getTipObjekta().contains(filterStatus)) {
										pronadjeni.add(sp);
									}
								}
							} else {
								if (sp.getTipObjekta().contains(filterStatus)) {
									pronadjeni.add(sp);
								}
							}
						}
					}
				}
			}
		}
		return pronadjeni;
	}

	public void connectSportskiObjekatLokacija() {
		ArrayList<Lokacija> lokacije = new ArrayList<Lokacija>(LokacijaDAO.getInstance().findAll());
		for (SportskiObjekat sportskiObjekat : sportskiObjekti.values()) {
			int idTrazeni = sportskiObjekat.getLokacija().getIntId();

			for (Lokacija lokacija : lokacije) {
				if (lokacija.getIntId() == idTrazeni) {
					sportskiObjekat.setLokacija(lokacija);
					break;
				}
			}
		}
	}

}
