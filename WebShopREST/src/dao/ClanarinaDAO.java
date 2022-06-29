package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Clanarina;
import beans.Korisnik;
import utils.DateHelper;

public class ClanarinaDAO {
	
	private static ClanarinaDAO clanarinaInstance = null;
	private static String contextPath = "";
	
	public HashMap<Integer, Clanarina> clanarine = new HashMap<Integer, Clanarina>();
	
	private ClanarinaDAO() {
		
	}
	
	private ClanarinaDAO(String contextPath) {
		loadClanarine(contextPath);
	}
	
	public static ClanarinaDAO getInstance() {
		if(clanarinaInstance == null) {
			clanarinaInstance = new ClanarinaDAO();
		}
		
		return clanarinaInstance;
	}
	
	public Collection<Clanarina> findAll(){
		return clanarine.values();
	}
	
	public Clanarina save(Clanarina clanarina) {
		Integer maxId = -1;
		for (int id : clanarine.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		clanarina.setIntId(maxId);
		clanarine.put(clanarina.getIntId(), clanarina);
		return clanarina;
	}
	
	public Clanarina update(Clanarina clanarina) {
		clanarine.put(clanarina.getIntId(), clanarina);
		return clanarina;
	}
	
	public void delete(int id) {
		this.clanarine.remove(id);
	}
	
	public void loadClanarine(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/files/clanarine.txt"); //paket
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, ID = "", tipClanarine = "", status = "";
			LocalDate datumPlacanja = LocalDate.now();
			LocalDate pocetniDatumVazenja = LocalDate.now();
			LocalDate krajnjiDatumVazenja = LocalDate.now();
			double punaCena = -1;
			int brojTermina = -1;
			int intId = -1;
			Korisnik korisnik = null;
			//Korisnik korisnik = new Korisnik();
			
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					intId = Integer.parseInt(st.nextToken().trim());
					ID = st.nextToken().trim();
					tipClanarine = st.nextToken().trim();
					datumPlacanja = DateHelper.stringToDate(st.nextToken().trim());
					pocetniDatumVazenja = DateHelper.stringToDate(st.nextToken().trim());
					krajnjiDatumVazenja = DateHelper.stringToDate(st.nextToken().trim());
					punaCena = Double.parseDouble(st.nextToken().trim());
					int korisnikId = Integer.parseInt(st.nextToken().trim());
					if(korisnikId != -1) {
						korisnik = new Korisnik(korisnikId);
					}
					status = st.nextToken().trim();
					brojTermina = Integer.parseInt(st.nextToken().trim());
				}
				clanarine.put(intId, new Clanarina(intId, ID, tipClanarine,datumPlacanja, pocetniDatumVazenja, krajnjiDatumVazenja, punaCena, korisnik, status, brojTermina)); //Drugi konstruktor
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
	
	public void sacuvajClanarine() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/clanarine.txt"); //proveri naziv fajla
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for(Clanarina clanarina : clanarine.values()) {
				out.write(clanarina.convertToString() + '\n');
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
	
	// da li treba connect clanarina <-> korisnik 
	
	
}
