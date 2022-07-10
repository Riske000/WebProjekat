package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import beans.IstorijaTreninga;
import beans.Komentar;
import beans.Korisnik;
import beans.Trening;
import utils.TipTreninga;
import beans.SportskiObjekat;
import beans.TipKupca;

public class TreningDAO {

	private static TreningDAO treningInstance = null;
	private static String contextPath = "";

	public HashMap<Integer, Trening> treninzi = new HashMap<Integer, Trening>();

	private TreningDAO() {

	}

	private TreningDAO(String contextPath) {
		loadTreninzi(contextPath);
	}

	public static TreningDAO getInstance() {
		if (treningInstance == null) {
			treningInstance = new TreningDAO();
		}

		return treningInstance;
	}

	public Collection<Trening> findAll() {
		return treninzi.values();
	}

	public Collection<Korisnik> getTreneriZaSportskiObjekat(int idSportskogObjekta) {
		ArrayList<Korisnik> treneriZaSportskiObjekat = new ArrayList<Korisnik>();
		for (Trening trening : treninzi.values()) {
			if (trening.getObjekatGdePripada().getIntId() == idSportskogObjekta) {
				if (trening.getTrener() != null) {
					treneriZaSportskiObjekat.add(trening.getTrener());
				}
			}
		}
		return treneriZaSportskiObjekat;
	}

	public Trening findTrening(int id) {
		return treninzi.containsKey(id) ? treninzi.get(id) : null;
	}

	public Trening save(Trening trening) {
		Integer maxId = -1;
		for (Integer id : treninzi.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		trening.setIntId(maxId);
		treninzi.put(trening.getIntId(), trening);
		sacuvajTreninge();
		return trening;
	}

	public Trening update(Trening trening) {
		treninzi.put(trening.getIntId(), trening);
		sacuvajTreninge();
		return trening;
	}

	public void delete(int id) {
		this.treninzi.remove(id);
	}

	public void loadTreninzi(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;
		try {
			File file = new File(contextPath + "/files/treninzi.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, naziv = "", tipTreninga = "", opis = "", slika = "";
			double trajanje = 0;
			int id = -1;
			Korisnik trener = null;
			SportskiObjekat objekatGdePripada = new SportskiObjekat();
			StringTokenizer st;

			// SportskiObjekatDAO sod = new SportskiObjekatDAO();
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					naziv = st.nextToken().trim();
					tipTreninga = st.nextToken().trim();

					// sportskiObjekat = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					objekatGdePripada = new SportskiObjekat(Integer.parseInt(st.nextToken().trim()));

					// sportskiObjekat = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					// SportskiObjekat obj = new
					// SportskiObjekat(Integer.parseInt(st.nextToken().trim()));
					trajanje = Double.parseDouble(st.nextToken().trim());
					int trenerId = Integer.parseInt(st.nextToken().trim());

					if (trenerId != -1) {
						trener = new Korisnik(trenerId);
					}
					opis = st.nextToken().trim();
					slika = st.nextToken().trim();
				}
				treninzi.put(id, new Trening(id, naziv, tipTreninga, objekatGdePripada, trajanje, trener, opis, slika));
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

	public void sacuvajTreninge() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/treninzi.txt"); // proveri naziv fajla
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for (Trening trening : treninzi.values()) {
				out.write(trening.convertToString() + '\n');
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

	public void connectTreningSportskiObjekat() {
		ArrayList<SportskiObjekat> sportskiObjekti = new ArrayList<SportskiObjekat>(
				SportskiObjekatDAO.getInstance().findAll());
		for (Trening trening : treninzi.values()) {
			int idTrazeni = trening.getObjekatGdePripada().getIntId();

			for (SportskiObjekat sportskiObjekat : sportskiObjekti) {
				if (sportskiObjekat.getIntId() == idTrazeni) {
					trening.setObjekatGdePripada(sportskiObjekat);
					break;
				}
			}
		}
	}

	public void connectTreningTrener() {
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>(KorisnikDAO.getInstance().findAll());
		for (Trening trening : treninzi.values()) {
			if (trening.getTrener() == null) {
				continue;
			}
			int idTrazeni = trening.getTrener().getIntId();

			for (Korisnik korisnik : korisnici) {
				if (korisnik.getIntId() == idTrazeni) {
					trening.setTrener(korisnik);
					break;
				}
			}
		}
	}

	public ArrayList<Trening> getTreninziZaSportskiObjekat(int idSportskogObjekta) {
		ArrayList<Trening> treninziZaSportskiObjekat = new ArrayList<Trening>();
		for (Trening trening : treninzi.values()) {
			if (trening.getObjekatGdePripada().getIntId() == idSportskogObjekta) {
				treninziZaSportskiObjekat.add(trening);
			}
		}
		return treninziZaSportskiObjekat;
	}

	public ArrayList<Trening> getPersonalniTreninziZaTrenera(int idKorisnika) {
		ArrayList<Trening> personalniTreninziZaTrenera = new ArrayList<Trening>();
		for (Trening trening : treninzi.values()) {
			if (trening.getTrener() != null) {
				if ((trening.getTrener().getIntId() == idKorisnika)
						&& (trening.getTipTreninga().equals(TipTreninga.PERSONALNI))) {
					personalniTreninziZaTrenera.add(trening);
				}
			}
		}
		return personalniTreninziZaTrenera;
	}

	public ArrayList<Trening> getGrupniTreninziZaTrenera(int idKorisnika) {
		ArrayList<Trening> grupniTreninziZaTrenera = new ArrayList<Trening>();
		for (Trening trening : treninzi.values()) {
			if (trening.getTrener() != null) {
				if ((trening.getTrener().getIntId() == idKorisnika)
						&& (trening.getTipTreninga().equals(TipTreninga.GRUPNI))) {
					grupniTreninziZaTrenera.add(trening);
				}
			}
		}
		return grupniTreninziZaTrenera;
	}

	public boolean OtkaziTr(int id) {
		LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
		Trening tren = TreningDAO.getInstance().findTrening(id);
		HashMap<Integer, IstorijaTreninga> i = IstorijaTreningaDAO.getInstance().istorijeTreninga;
		for (IstorijaTreninga it : i.values()) {
			if (tren.getIntId() == it.getTrening().getIntId()) {
				if (it.getDatumVremePrijave().isBefore(yesterday)) {
					it.setTrening(null);
					delete(id);
					return true;
				} 
			}
		}
		return false;
	}

}
