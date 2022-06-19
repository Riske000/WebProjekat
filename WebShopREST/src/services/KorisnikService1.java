package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;




import beans.Korisnik;
import dao.KorisnikDAO;
import utils.PokretanjeProjekta;

@Path("/korisnik1")
public class KorisnikService1 {
	
	@Context
	ServletContext ctx;
	
	public KorisnikService1() {
		
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("korisnikDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	PokretanjeProjekta.getInstance(contextPath);
			ctx.setAttribute("korisnikDAO", KorisnikDAO.getInstance());
		}
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Korisnik register(Korisnik userToRegister) {

		KorisnikDAO korisnikDAO = (KorisnikDAO) ctx.getAttribute("korisnikDAO");

		return korisnikDAO.save(userToRegister);
		
	}

}
