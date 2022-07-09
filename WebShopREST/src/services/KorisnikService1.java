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
import dto.KorisnikDTO;
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
	public Collection<KorisnikDTO> getKorisnici() {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		Collection<Korisnik> korisnici = dao.findAll();
		ArrayList<KorisnikDTO> korisniciDTO = new ArrayList<KorisnikDTO>();
		for(Korisnik k : korisnici) {
			korisniciDTO.add(new KorisnikDTO(k));
		}
		return korisniciDTO;
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Korisnik register(Korisnik userToRegister) { //da li ce biti problem kad vraca trenera

		KorisnikDAO korisnikDAO = (KorisnikDAO) ctx.getAttribute("korisnikDAO");

		boolean retVal = korisnikDAO.postojiKorisnickoIme(userToRegister.getKorisnickoIme());
		if(retVal) {
			return null;			
		}
		Korisnik korisnik = korisnikDAO.save(userToRegister);
		return korisnik;
		
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Korisnik korisnik, @Context HttpServletRequest request) { // da li je problem ako se loguje trener
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
	public KorisnikDTO login(@Context HttpServletRequest request) {
		Korisnik logovani = (Korisnik) request.getSession().getAttribute("user");
		if(logovani == null) {
			return null;
		} else {
			KorisnikDTO logovaniDTO = new KorisnikDTO(logovani);
			return logovaniDTO;
		}
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<KorisnikDTO> search(@QueryParam("searchIme") String searchIme, @QueryParam("searchPrezime") String searchPrezime, 
			@QueryParam("searchKorisnickoIme") String searchKorisnickoIme) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		ArrayList<Korisnik> pronadjeni = dao.search(searchIme, searchPrezime, searchKorisnickoIme);
		if(pronadjeni.size() == 0) {
			return new ArrayList<KorisnikDTO>();
		} else {
			ArrayList<KorisnikDTO> pronadjeniDTO = new ArrayList<KorisnikDTO>();
			for(Korisnik k : pronadjeni) {
				pronadjeniDTO.add(new KorisnikDTO(k));
			}
			return pronadjeniDTO;
		}
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
	
	@PUT
	@Path("/cekirajSe")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cekirajSe(@Context HttpServletRequest request) {
		KorisnikDAO dao = (KorisnikDAO) ctx.getAttribute("korisnikDAO");
		Korisnik logovani = (Korisnik) request.getSession().getAttribute("user");
		if(dao.cekirajSe(logovani.getIntId())) {        
			return Response.status(200).build();
		} else {
			return Response.status(400).build();
		}
		
	}
}
