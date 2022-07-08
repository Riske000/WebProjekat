package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Trening;
import beans.ZakazanTrening;
import utils.DateTimeHelper;

public class ZakazanTreningDAO {

	private static ZakazanTreningDAO zakazanTreningInstance = null;
	private static String contextPath = "";

	public HashMap<Integer, ZakazanTrening> zakazaniTreninzi = new HashMap<Integer, ZakazanTrening>();

	ZakazanTreningDAO() {

	}

	private ZakazanTreningDAO(String contextPath) {
		loadZakazaniTreninzi(contextPath);
	}

	public static ZakazanTreningDAO getInstance() {
		if (zakazanTreningInstance == null) {
			zakazanTreningInstance = new ZakazanTreningDAO();
		}

		return zakazanTreningInstance;
	}

	public Collection<ZakazanTrening> findAll() {
		return zakazaniTreninzi.values();
	}

	public ZakazanTrening findZakazanTrening(int id) {
		return zakazaniTreninzi.containsKey(id) ? zakazaniTreninzi.get(id) : null;
	}

	public ZakazanTrening save(ZakazanTrening zakazanTrening) {
		Integer maxId = -1;
		for (Integer id : zakazaniTreninzi.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		zakazanTrening.setIntId(maxId);
		zakazaniTreninzi.put(zakazanTrening.getIntId(), zakazanTrening);
		return zakazanTrening;
	}

	public ZakazanTrening update(ZakazanTrening zakazanTrening) {
		zakazaniTreninzi.put(zakazanTrening.getIntId(), zakazanTrening);
		return zakazanTrening;
	}

	public void delete(int id) {
		this.zakazaniTreninzi.remove(id);
	}

	public void loadZakazaniTreninzi(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;
		try {
			File file = new File(contextPath + "/files/zakazaniTreninzi.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, statusTreninga = "";
			Trening trening = new Trening();
			int id = -1;
			LocalDateTime terminTreninga = LocalDateTime.now();
			StringTokenizer st;

			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");

				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					trening = new Trening(Integer.parseInt(st.nextToken().trim()));
					terminTreninga = DateTimeHelper.stringToDateTime(st.nextToken().trim());
					statusTreninga = st.nextToken().trim();
				}
				zakazaniTreninzi.put(id, new ZakazanTrening(id, trening, terminTreninga, statusTreninga));
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

	public void sacuvajZakazaneTreninge() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/zakazaniTreninzi.txt");
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));
			
			for(ZakazanTrening zakazanTrening : zakazaniTreninzi.values()) {
				out.write(zakazanTrening.convertToString() + '\n');
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
	
	public void connectZakazanTreningTrening() {
		ArrayList<Trening> treninzi = new ArrayList<Trening>(TreningDAO.getInstance().findAll());
		for(ZakazanTrening zakazanTrening : zakazaniTreninzi.values()) {
			int idTrazeni = zakazanTrening.getTrening().getIntId();
			
			for(Trening trening : treninzi) {
				if(trening.getIntId() == idTrazeni) {
					zakazanTrening.setTrening(trening);
					break;
				}
			}
		}
	}

}
