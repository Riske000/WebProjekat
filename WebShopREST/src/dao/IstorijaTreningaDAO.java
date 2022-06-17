package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.IstorijaTreninga;
import utils.DateHelper;

public class IstorijaTreningaDAO {
	
	private static IstorijaTreningaDAO istorijaTreningaInstance = null;
	
	private HashMap<Integer, IstorijaTreninga> istorijeTreninga = new HashMap<Integer,IstorijaTreninga>();
	
	private IstorijaTreningaDAO(){
		
	}
	
	private IstorijaTreningaDAO(String contextPath){
		loadIstorijeTreninga(contextPath);
	}
	
	public static IstorijaTreningaDAO getInstance() {
		if(istorijaTreningaInstance == null) {
			istorijaTreningaInstance = new IstorijaTreningaDAO();
		}
		
		return istorijaTreningaInstance;
	}
	
	public Collection<IstorijaTreninga> findAll(){
		return istorijeTreninga.values();
	}
	
	public IstorijaTreninga save(IstorijaTreninga istorijaTreninga) {
		Integer maxId = -1;
		for (int id : istorijeTreninga.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		istorijaTreninga.setIntId(maxId);
		istorijeTreninga.put(istorijaTreninga.getIntId(), istorijaTreninga);
		return istorijaTreninga;
	}
	
	public IstorijaTreninga update(IstorijaTreninga istorijaTreninga) {
		istorijeTreninga.put(istorijaTreninga.getIntId(), istorijaTreninga);
		return istorijaTreninga;
	}
	
	public void delete(int id) {
		this.istorijeTreninga.remove(id);
	}
	
	private void loadIstorijeTreninga(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/istorijeTreninga.txt"); // Dodati i paket u putanju
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line;
			LocalDate datumVremePrijave = LocalDate.now();
			int intId = -1;
			
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					intId = Integer.parseInt(st.nextToken().trim());
					datumVremePrijave = DateHelper.stringToDate(st.nextToken().trim()); // ovo je localDateTime
				}
				istorijeTreninga.put(intId, new IstorijaTreninga()); //Promeniti konstruktor
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
