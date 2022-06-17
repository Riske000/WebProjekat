package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Korisnik;
import utils.DateHelper;

public class KorisnikDAO {

	private HashMap<Integer, Korisnik> korisnici = new HashMap<Integer, Korisnik>();
	
	public KorisnikDAO() {
		
	}
	
	public KorisnikDAO(String contextPath) {
		loadKorisnici(contextPath);
	}
	
	public Collection<Korisnik> findAll(){
		return korisnici.values();
	}
	
	public Korisnik save(Korisnik korisnik) {
		Integer maxId = -1;
		for (int id : korisnici.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		korisnik.setIntId(maxId);
		korisnici.put(korisnik.getIntId(), korisnik);
		return korisnik;
	}
	
	public Korisnik update(Korisnik korisnik) {
		korisnici.put(korisnik.getIntId(), korisnik);
		return korisnik;
	}
	
	public void delete(int id) {
		this.korisnici.remove(id);
	}
	
	private void loadKorisnici(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/korisnici.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, korisnickoIme = "", sifra = "", ime = "", prezime = "", pol = "", uloga = "";
			LocalDate datumRodjenja = LocalDate.now();
			int intId = -1;
			
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					intId = Integer.parseInt(st.nextToken().trim());
					korisnickoIme = st.nextToken().trim();
					sifra = st.nextToken().trim();
					ime = st.nextToken().trim();
					prezime = st.nextToken().trim();
					pol = st.nextToken().trim();
					datumRodjenja = DateHelper.stringToDate(st.nextToken().trim());
					uloga = st.nextToken().trim();
				}
				korisnici.put(intId, new Korisnik(intId, korisnickoIme, sifra, ime, prezime, pol, datumRodjenja, uloga, null, null, null, -1, null, null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		
	}
}
