package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	private static String contextPath = "";
	
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
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/files/istorijeTreninga.txt"); // Dodati i paket u putanju
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line;
			LocalDateTime datumVremePrijave = LocalDateTime.now();
			int intId = -1;
			Trening trening = null;
			Korisnik kupac = null;
			Korisnik trener = null;
			
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					intId = Integer.parseInt(st.nextToken().trim());
					datumVremePrijave = DateTimeHelper.stringToDateTime(st.nextToken().trim());
					int treningId = Integer.parseInt(st.nextToken().trim());
					if(treningId != -1) {
						trening = new Trening(treningId);
					}
					
					int kupacId = Integer.parseInt(st.nextToken().trim());
					if(kupacId != -1) {
						kupac = new Korisnik(kupacId);
					}
					
					int trenerId = Integer.parseInt(st.nextToken().trim());
					if(trenerId != -1) {
						trener = new Korisnik(trenerId);
					}
				}
				istorijeTreninga.put(intId, new IstorijaTreninga(intId, datumVremePrijave, trening, kupac, trener));
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
	
	public void sacuvajIstorijeTreninga() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/istorijeTreninga.txt"); //proveri naziv fajla
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for(IstorijaTreninga istorijaTreninga : istorijeTreninga.values()) {
				out.write(istorijaTreninga.convertToString() + '\n');
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
	
	public void connectIstorijaTreningaKupacTrener() {
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>(KorisnikDAO.getInstance().findAll());
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
		ArrayList<Trening> treninzi = new ArrayList<Trening>(TreningDAO.getInstance().findAll());
		for(IstorijaTreninga istorijaTreninga : istorijeTreninga.values()) {
			if(istorijaTreninga.getTrening() == null) {
				continue;
			}
			int idTrazeni = istorijaTreninga.getTrening().getIntId();
			
			for(Trening trening : treninzi) {
				if(trening.getIntId() == idTrazeni) {
					istorijaTreninga.setTrening(trening);
					break;
				}
			}
		}
	}
	
	public void connectIstorijaTreningaKupac() {
		ArrayList<Korisnik> kupci = new ArrayList<Korisnik>(KorisnikDAO.getInstance().findAll());
		for(IstorijaTreninga it : istorijeTreninga.values()) {
			if(it.getKupac() == null) {
				continue;
			}
			int idTrazeni = it.getKupac().getIntId();
			
			for(Korisnik k : kupci) {
				if(k.getIntId() == idTrazeni) {
					it.setKupac(k);
					break;
				}
			}
			
		}
	}
	
	public void connectIstorijaTreningaTrener() {
		ArrayList<Korisnik> treneri = new ArrayList<Korisnik>(KorisnikDAO.getInstance().findAll());
		for(IstorijaTreninga it : istorijeTreninga.values()) {
			if(it.getTrener() == null) {
				continue;
			}
			int idTrazeni = it.getTrener().getIntId();
			
			for(Korisnik t : treneri) {
				if(t.getIntId() == idTrazeni) {
					it.setTrener(t);
					t.getIstorijaTreninga().add(it);
					break;
				}
			}
			
		}
	}
}
