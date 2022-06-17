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
import beans.SportskiObjekat;
import dao.SportskiObjekatDAO;

public class KomentarDAO {

private HashMap<Integer, Komentar> komentari = new HashMap<Integer, Komentar>();
	
	public KomentarDAO() {
		
	}
	
	

	public KomentarDAO(String contextPath) {
		loadKomentari(contextPath);
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

	
	private void loadKomentari(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/komentari.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line,  tekstKomentara = "" ;
			int ocena = 1;
			int id = -1;
			Korisnik korisnik;
			SportskiObjekat sportskiObjekat;
			StringTokenizer st;
			SportskiObjekatDAO sod = new SportskiObjekatDAO();
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					// korisnik
					//sportskiObjekat = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					//SportskiObjekat obj = new SportskiObjekat(Integer.parseInt(st.nextToken().trim()));
					tekstKomentara = st.nextToken().trim();
					ocena = Integer.parseInt(st.nextToken().trim());
				}
				komentari.put(id, new Komentar(id, null, null, tekstKomentara, ocena));
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
