package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Clanarina;
import beans.Korisnik;
import beans.SportskiObjekat;
import beans.Trening;
import beans.ZakazanTrening;
import utils.DateTimeHelper;
import utils.TipTreninga;

public class ZakazanTreningDAO {

	private static ZakazanTreningDAO zakazanTreningInstance = null;
	private static String contextPath = "";

	public HashMap<Integer, ZakazanTrening> zakazaniTreninzi = new HashMap<Integer, ZakazanTrening>();

	ZakazanTreningDAO() {

	}

	private ZakazanTreningDAO(String contextPath) {
		loadZakazaniTreninzi(contextPath);
	}

	public static ZakazanTreningDAO getInstance() {
		if (zakazanTreningInstance == null) {
			zakazanTreningInstance = new ZakazanTreningDAO();
		}

		return zakazanTreningInstance;
	}

	public Collection<ZakazanTrening> findAll() {
		return zakazaniTreninzi.values();
	}

	public ZakazanTrening findZakazanTrening(int id) {
		return zakazaniTreninzi.containsKey(id) ? zakazaniTreninzi.get(id) : null;
	}

	public ZakazanTrening save(ZakazanTrening zakazanTrening) {
		Integer maxId = -1;
		for (Integer id : zakazaniTreninzi.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		zakazanTrening.setIntID(maxId);
		zakazaniTreninzi.put(zakazanTrening.getIntID(), zakazanTrening);
		sacuvajZakazaneTreninge();
		return zakazanTrening;
	}

	public ZakazanTrening update(ZakazanTrening zakazanTrening) {
		
		if (zakazanTrening.getKupac() != null) {
			int id = zakazanTrening.getKupac().getIntId();
			Korisnik kupac = KorisnikDAO.getInstance().find(id);
			zakazanTrening.setKupac(kupac);
		}
		
		if (zakazanTrening.getTrener() != null) {
			int id = zakazanTrening.getTrener().getIntId();
			Korisnik trener = KorisnikDAO.getInstance().find(id);
			zakazanTrening.setTrener(trener);
		}
		
		if (zakazanTrening.getObjekatGdePripada() != null) {
			int id = zakazanTrening.getObjekatGdePripada().getIntId();
			SportskiObjekat obejkat = SportskiObjekatDAO.getInstance().findObjekat(id);
			zakazanTrening.setObjekatGdePripada(obejkat);
		}
		
		zakazaniTreninzi.put(zakazanTrening.getIntID(), zakazanTrening);
		return zakazanTrening;
	}

	public boolean delete(int id) {
		if(LocalDateTime.now().plusDays(2).isBefore(DateTimeHelper.stringToDateTime(zakazaniTreninzi.get(id).getTerminTreninga())))
		{		
			this.zakazaniTreninzi.remove(id);
			sacuvajZakazaneTreninge();
			return true;
		}else {
			return false;
		}
	}

	public void loadZakazaniTreninzi(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;
		try {
			File file = new File(contextPath + "/files/zakazaniTreninzi.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, statusTreninga = "";
			
			int id = -1;
			LocalDateTime terminTreninga = LocalDateTime.now();
			StringTokenizer st;

			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");

				while (st.hasMoreTokens()) {
					Korisnik kupac = null;
					Korisnik trener = null;
					SportskiObjekat objekat = null;
					id = Integer.parseInt(st.nextToken().trim());
					terminTreninga = DateTimeHelper.stringToDateTime(st.nextToken().trim());
					statusTreninga = st.nextToken().trim();
					kupac = new Korisnik(Integer.parseInt(st.nextToken().trim()));
					trener = new Korisnik(Integer.parseInt(st.nextToken().trim()));
					objekat = new SportskiObjekat(Integer.parseInt(st.nextToken().trim()));
					zakazaniTreninzi.put(id, new ZakazanTrening(id, terminTreninga, statusTreninga, kupac, trener, objekat));
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

	public void sacuvajZakazaneTreninge() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/zakazaniTreninzi.txt");
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));
			
			for(ZakazanTrening zakazanTrening : zakazaniTreninzi.values()) {
				out.write(zakazanTrening.convertToString() + '\n');
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
	
	public void connectZakazanTreningKupac() {
		ArrayList<Korisnik> kupci = new ArrayList<Korisnik>(KorisnikDAO.getInstance().findAll());
		for(ZakazanTrening zakazanTrening : zakazaniTreninzi.values()) {
			int idTrazeni = zakazanTrening.getKupac().getIntId();
			
			for(Korisnik kupac : kupci) {
				if(kupac.getIntId() == idTrazeni) {
					zakazanTrening.setKupac(kupac);
					break;
				}
			}
		}
	}
	
	public void connectZakazanTreningTrener() {
		ArrayList<Korisnik> treneri = new ArrayList<Korisnik>(KorisnikDAO.getInstance().findAll());
		for(ZakazanTrening zakazanTrening : zakazaniTreninzi.values()) {
			int idTrazeni = zakazanTrening.getTrener().getIntId();
			
			for(Korisnik trener : treneri) {
				if(trener.getIntId() == idTrazeni) {
					zakazanTrening.setTrener(trener);
					break;
				}
			}
		}
	}
	
	public void connectZakazanTreningObjekatGdePripada() {
		ArrayList<SportskiObjekat> objekti = new ArrayList<SportskiObjekat>(SportskiObjekatDAO.getInstance().findAll());
		for(ZakazanTrening zakazanTrening : zakazaniTreninzi.values()) {
			int idTrazeni = zakazanTrening.getObjekatGdePripada().getIntId();
			
			for(SportskiObjekat objekat : objekti) {
				if(objekat.getIntId() == idTrazeni) {
					zakazanTrening.setObjekatGdePripada(objekat);
					break;
				}
			}
		}
	}
	
	public ArrayList<ZakazanTrening> getPersonalniTreninziZaTrenera(int idKorisnika) {
		ArrayList<ZakazanTrening> personalniTreninziZaTrenera = new ArrayList<ZakazanTrening>();
		for (ZakazanTrening trening : zakazaniTreninzi.values()) {
			if (trening.getTrener() != null) {
				if (trening.getTrener().getIntId() == idKorisnika) {
					personalniTreninziZaTrenera.add(trening);
				}
			}
		}
		return personalniTreninziZaTrenera;
	}

}
