package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Clanarina;
import beans.IstorijaTreninga;
import beans.Korisnik;
import beans.Trening;
import utils.DateHelper;
import utils.DateTimeHelper;

public class IstorijaTreningaDAO {
	
	private static IstorijaTreningaDAO istorijaTreningaInstance = null;
	
	public HashMap<Integer, IstorijaTreninga> istorijeTreninga = new HashMap<Integer,IstorijaTreninga>();
	
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
	
	public void loadIstorijeTreninga(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/istorijeTreninga.txt"); // Dodati i paket u putanju
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line;
			LocalDateTime datumVremePrijave = LocalDateTime.now();
			int intId = -1;
			Trening trening = new Trening();
			Korisnik kupac = new Korisnik();
			Korisnik trener = new Korisnik();
			
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					intId = Integer.parseInt(st.nextToken().trim());
					datumVremePrijave = DateTimeHelper.stringToDateTime(st.nextToken().trim());
					trening = new Trening(Integer.parseInt(st.nextToken().trim()));
					kupac = new Korisnik(Integer.parseInt(st.nextToken().trim()));
					trener = new Korisnik(Integer.parseInt(st.nextToken().trim()));
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
	
	public void connectIstorijaTreningaKupacTrener() {
		ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) KorisnikDAO.getInstance().findAll();
		for(IstorijaTreninga istorijaTreninga : istorijeTreninga.values()) {
			int idTrazeniKupac = istorijaTreninga.getKupac().getIntId();
			int idTrazeniTrener = istorijaTreninga.getTrener().getIntId();
			
			for(Korisnik korisnik : korisnici) {
				if(korisnik.getIntId() == idTrazeniKupac) {
					istorijaTreninga.setKupac(korisnik);
					break;
				} else if(korisnik.getIntId() == idTrazeniTrener) {
					istorijaTreninga.setTrener(korisnik);
					break;
				}
			}
		}
	}
	
	public void connectIstorijaTreningaTrening() {
		ArrayList<Trening> treninzi = (ArrayList<Trening>) TreningDAO.getInstance().findAll();
		for(IstorijaTreninga istorijaTreninga : istorijeTreninga.values()) {
			int idTrazeni = istorijaTreninga.getTrening().getIntId();
			
			for(Trening trening : treninzi) {
				if(trening.getIntId() == idTrazeni) {
					istorijaTreninga.setTrening(trening);
					break;
				}
			}
		}
	}
}
