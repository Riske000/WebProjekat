package utils;

import dao.ClanarinaDAO;
import dao.KorisnikDAO;

public class PointsChangeThread extends Thread{
	private static int time = 10000;
	
	public void run(){
	       while(true) {
	    	   try {
				Thread.sleep(time);
				
				
				ClanarinaDAO.getInstance().clanarinaIstekla();
				KorisnikDAO.getInstance().setImeTipaKupca();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	       }
	 }

}
