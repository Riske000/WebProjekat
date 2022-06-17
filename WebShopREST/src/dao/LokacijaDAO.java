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

import beans.Lokacija;

public class LokacijaDAO {

private HashMap<Integer, Lokacija> lokacije = new HashMap<Integer, Lokacija>();
	
	public LokacijaDAO() {
		
	}
	
	

	public LokacijaDAO(String contextPath) {
		loadLokacije(contextPath);
	}

	
	public Collection<Lokacija> findAll() {
		return lokacije.values();
	}

	
	public Lokacija findLokacija(int id) {
		return lokacije.containsKey(id) ? lokacije.get(id) : null;
	}
	
	
	public Lokacija save(Lokacija lokacija) {
		Integer maxId = -1;
		for (Integer id : lokacije.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		lokacija.setIntId(maxId);
		lokacije.put(lokacija.getIntId(), lokacija);
		return lokacija;
	}
	
	public Lokacija update(Lokacija lokacija) {
		lokacije.put(lokacija.getIntId(), lokacija);
		return lokacija;
	}
	
	public void delete(String id) {
		this.lokacije.remove(id);
	}

	
	private void loadLokacije(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/lokacije.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line,  ulica = "", broj = "", mesto = "", postanskiBroj ="" ;
			double geografskaSirina = 0;
			double geografskaDuzina = 0;
			int id = -1;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					geografskaSirina = Double.parseDouble(st.nextToken().trim());
					geografskaDuzina = Double.parseDouble(st.nextToken().trim());
					ulica = st.nextToken().trim();
					broj = st.nextToken().trim();
					mesto = st.nextToken().trim();
					postanskiBroj = st.nextToken().trim();
				}
				lokacije.put(id, new Lokacija(id, geografskaSirina, geografskaDuzina,
						ulica, broj, mesto, postanskiBroj));
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
