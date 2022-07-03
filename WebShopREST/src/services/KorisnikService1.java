package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Korisnik;
import beans.SportskiObjekat;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
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
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> getKorisnici() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.findAll();
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(Korisnik userToRegister) {

		KorisnikDAO korisnikDAO = (KorisnikDAO) ctx.getAttribute("korisnikDAO");

		boolean retVal = korisnikDAO.postojiKorisnickoIme(userToRegister.getKorisnickoIme());
		if(retVal) {
			return Response.status(400).entity("Korisnicko ime vec postoji!").build();			
		}
		korisnikDAO.save(userToRegister);
		return Response.status(200).build();
		
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Korisnik korisnik, @Context HttpServletRequest request) {
		KorisnikDAO korisnikDao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		Korisnik logovaniKorisnik = korisnikDao.checkKorisnickoImeSifra(korisnik.getKorisnickoIme(), korisnik.getSifra());
		if (logovaniKorisnik == null) {
			return Response.status(400).entity("Pogresno korisnicko ime ili sifra!").build();
		}
		request.getSession().setAttribute("user", logovaniKorisnik);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Korisnik login(@Context HttpServletRequest request) {
		return (Korisnik) request.getSession().getAttribute("user");
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Korisnik> search(@QueryParam("searchIme") String searchIme, @QueryParam("searchPrezime") String searchPrezime, 
			@QueryParam("searchKorisnickoIme") String searchKorisnickoIme) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.search(searchIme, searchPrezime, searchKorisnickoIme);
	}
	
	@GET
	@Path("/getKupci")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> getKupciZaSportskiObjekat(@QueryParam("idSportskogObjekta") int idSportskogObjekta) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.getKupciZaSportskiObjekat(idSportskogObjekta);
	}
	
	@GET
	@Path("/freeManagers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> getSlobodniMenadzeri() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.getSlobodniMenadzeri();
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Korisnik changeOne(Korisnik korisnik) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		return dao.update(korisnik);
	}
}
