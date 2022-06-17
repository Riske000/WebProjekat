package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import beans.IstorijaTreninga;
import beans.Komentar;
import beans.Korisnik;
import beans.SportskiObjekat;
import beans.Trening;
import dao.SportskiObjekatDAO;

public class KomentarDAO {

	
	private static KomentarDAO komentarInstance = null;
	
	public HashMap<Integer, Komentar> komentari = new HashMap<Integer, Komentar>();

	private KomentarDAO() {

	}

	private KomentarDAO(String contextPath) {
		loadKomentari(contextPath);
	}
	
	
	public static KomentarDAO getInstance()
    {
        if (komentarInstance == null) {
        	komentarInstance = new KomentarDAO();
        }
 
        return komentarInstance;
    }
	

	public Collection<Komentar> findAll() {
		return komentari.values();
	}

	public Komentar findKomentar(int id) {
		return komentari.containsKey(id) ? komentari.get(id) : null;
	}

	public Komentar save(Komentar komentar) {
		Integer maxId = -1;
		for (Integer id : komentari.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		komentar.setIntId(maxId);
		komentari.put(komentar.getIntId(), komentar);
		return komentar;
	}

	public Komentar update(Komentar komentar) {
		komentari.put(komentar.getIntId(), komentar);
		return komentar;
	}

	public void delete(String id) {
		this.komentari.remove(id);
	}

	public void loadKomentari(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/komentari.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, tekstKomentara = "";
			int ocena = 1;
			int id = -1;
			Korisnik korisnik = new Korisnik();
			SportskiObjekat sportskiObjekat = new SportskiObjekat();
			StringTokenizer st;
			//SportskiObjekatDAO sod = new SportskiObjekatDAO();
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					// korisnik

					//sportskiObjekat = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					korisnik = new Korisnik(Integer.parseInt(st.nextToken().trim()));
					sportskiObjekat = new SportskiObjekat(Integer.parseInt(st.nextToken().trim()));

					// sportskiObjekat = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					// SportskiObjekat obj = new
					// SportskiObjekat(Integer.parseInt(st.nextToken().trim()));

					tekstKomentara = st.nextToken().trim();
					ocena = Integer.parseInt(st.nextToken().trim());
				}
				komentari.put(id, new Komentar(id, korisnik, sportskiObjekat, tekstKomentara, ocena));
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
	
	public void connectKomentarKupac() {
		ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) KorisnikDAO.getInstance().findAll();
		for(Komentar komentar : komentari.values()) {
			int idTrazeni = komentar.getKupac().getIntId();
			
			for(Korisnik korisnik : korisnici) {
				if(korisnik.getIntId() == idTrazeni) {
					komentar.setKupac(korisnik);
					break;
				}
			}
		}
	}
	
	public void connectKomentarSportskiObjekat() {
		ArrayList<SportskiObjekat> sportskiObjekti = (ArrayList<SportskiObjekat>) SportskiObjekatDAO.getInstance().findAll();
		for(Komentar komentar : komentari.values()) {
			int idTrazeni = komentar.getSportskiObjekat().getIntId();
			
			for(SportskiObjekat sportskiObjekat : sportskiObjekti) {
				if(sportskiObjekat.getIntId() == idTrazeni) {
					komentar.setSportskiObjekat(sportskiObjekat);
					break;
				}
			}
		}
	}

}
