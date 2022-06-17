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

import beans.Komentar;
import beans.Korisnik;
import beans.Trening;
import beans.SportskiObjekat;


public class TreningDAO {

private HashMap<Integer, Trening> treninzi = new HashMap<Integer, Trening>();
	
	public TreningDAO() {
		
	}
	
	

	public TreningDAO(String contextPath) {
		loadTreninzi(contextPath);
	}

	
	public Collection<Trening> findAll() {
		return treninzi.values();
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
		return trening;
	}
	
	public Trening update(Trening trening) {
		treninzi.put(trening.getIntId(), trening);
		return trening;
	}
	
	public void delete(String id) {
		this.treninzi.remove(id);
	}

	
	private void loadTreninzi(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/treninzi.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, naziv = "",  tipTreninga = "" , opis = "", slika = "";
			double trajanje = 0;
			int id = -1;
			Korisnik trener;
			SportskiObjekat objekatGdePripada;
			StringTokenizer st;
			
			SportskiObjekatDAO sod = new SportskiObjekatDAO();
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					naziv = st.nextToken().trim();
					tipTreninga = st.nextToken().trim();
					//sportskiObjekat = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					//SportskiObjekat obj = new SportskiObjekat(Integer.parseInt(st.nextToken().trim()));
					trajanje = Double.parseDouble(st.nextToken().trim());
					// korisnik trener
					opis = st.nextToken().trim();
					slika = st.nextToken().trim();
				}
				treninzi.put(id, new Trening(id, naziv, tipTreninga, null, trajanje, null, opis, slika));
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
