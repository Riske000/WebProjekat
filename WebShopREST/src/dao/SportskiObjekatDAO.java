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

import beans.SportskiObjekat;

public class SportskiObjekatDAO {

private HashMap<Integer, SportskiObjekat> sportskiObjekti = new HashMap<Integer, SportskiObjekat>();
	
	public SportskiObjekatDAO() {
		
	}
	
	

	public SportskiObjekatDAO(String contextPath) {
		loadSportskiObjekti(contextPath);
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
		sportskiObjekti.put(sportskiObjekat.getIntId(), sportskiObjekat);
		return sportskiObjekat;
	}
	
	public SportskiObjekat update(SportskiObjekat sportskiObjekat) {
		sportskiObjekti.put(sportskiObjekat.getIntId(), sportskiObjekat);
		return sportskiObjekat;
	}
	
	public void delete(String id) {
		this.sportskiObjekti.remove(id);
	}

	
	private void loadSportskiObjekti(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/sportskiobjekti.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, ime = "", tipObjekta = "", status = "", logoObjekta = "";
			List<String>sadrzajObjekta = new ArrayList<String>();
			double prosecnaOcena = 0;
			LocalTime pocetak = LocalTime.now();
			LocalTime kraj = LocalTime.now();
			int id = -1;
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
					//za sadrzaj
					status = st.nextToken().trim();
					//lokacija = st.nextToken().trim();
					logoObjekta = st.nextToken().trim();
					prosecnaOcena = Double.parseDouble(st.nextToken().trim());
					// za vremena konverter
					// za vremena konverter
				}
				sportskiObjekti.put(id, new SportskiObjekat(id, ime, tipObjekta,
						sadrzajObjekta, status, null, logoObjekta, prosecnaOcena,
						pocetak, kraj));
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
