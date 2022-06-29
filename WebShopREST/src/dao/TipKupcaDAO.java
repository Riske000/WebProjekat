package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Lokacija;
import beans.TipKupca;

public class TipKupcaDAO {
	
	private static TipKupcaDAO tipKupcaInstance = null;
	private static String contextPath = "";
	
	private HashMap<Integer, TipKupca> tipoviKupca = new HashMap<Integer, TipKupca>();

	private TipKupcaDAO() {

	}

	private TipKupcaDAO(String contextPath) {
		loadTipoviKupca(contextPath);
	}
	
	public static TipKupcaDAO getInstance()
    {
        if (tipKupcaInstance == null) {
        	tipKupcaInstance = new TipKupcaDAO();
        }
 
        return tipKupcaInstance;
    }

	public Collection<TipKupca> findAll() {
		return tipoviKupca.values();
	}

	public TipKupca save(TipKupca tipKupca) {
		Integer maxId = -1;
		for (int id : tipoviKupca.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		tipKupca.setIntId(maxId);
		tipoviKupca.put(tipKupca.getIntId(), tipKupca);
		return tipKupca;
	}

	public TipKupca update(TipKupca tipKupca) {
		tipoviKupca.put(tipKupca.getIntId(), tipKupca);
		return tipKupca;
	}

	public void delete(int id) {
		this.tipoviKupca.remove(id);
	}

	public void loadTipoviKupca(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;
		try {
			File file = new File(contextPath + "/files/tipoviKupca.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, imeTipa = "";
			double popust = -1;
			double potrebniPoeni = -1;
			int intId = -1;

			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					intId = Integer.parseInt(st.nextToken().trim());
					imeTipa = st.nextToken().trim();
					popust = Double.parseDouble(st.nextToken().trim());
					potrebniPoeni = Double.parseDouble(st.nextToken().trim());
				}
				tipoviKupca.put(intId, new TipKupca(intId, imeTipa, popust, potrebniPoeni));
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
	
	public void sacuvajTipoveKupca() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "/files/tipoviKupca.txt"); //proveri naziv fajla
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for(TipKupca tipKupca : tipoviKupca.values()) {
				out.write(tipKupca.convertToString() + '\n');
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
}
