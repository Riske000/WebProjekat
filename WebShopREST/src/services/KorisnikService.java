package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Korisnik;
import dao.KorisnikDAO;

@Path("/korisnik")
public class KorisnikService {
	
	@Context
	ServletContext ctx;
	
	public KorisnikService() {
		
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("korisnikDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("korisnikDAO", KorisnikDAO.getInstance());
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Korisnik korisnik, @Context HttpServletRequest request) {
		KorisnikDAO korisnikDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		Korisnik logovaniKorisnik = korisnikDao.checkKorisnickoImeSifra(korisnik.getKorisnickoIme(), korisnik.getSifra());
		if (logovaniKorisnik == null) {
			return Response.status(400).entity("Invalid username and/or password").build();
		}
		request.getSession().setAttribute("user", logovaniKorisnik);
		return Response.status(200).build();
	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Korisnik login(@Context HttpServletRequest request) {
		return (Korisnik) request.getSession().getAttribute("user");
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Korisnik userToRegister) {

		if (userToRegister.getKorisnickoIme() == null || userToRegister.getSifra() == null
				|| userToRegister.getKorisnickoIme().equals("") || userToRegister.getKorisnickoIme().equals("")) {
			return Response.status(400).entity("Username i password su obavezna polja.").build();
		}
		
		KorisnikDAO korisnikDAO = (KorisnikDAO) ctx.getAttribute("korisnikDAO");

		if (korisnikDAO.checkKorisnickoIme(userToRegister.getKorisnickoIme()) != null) {
			return Response.status(400).entity("Username koji ste uneli vec je zauzet.").build();
		} else {
			korisnikDAO.save(userToRegister);
			return Response.status(200).build();
		}
	}

}
