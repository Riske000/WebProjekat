package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Korisnik;
import beans.SportskiObjekat;
import beans.Trening;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;
import dto.KorisnikDTO;
import utils.PokretanjeProjekta;

@Path("/trening")
public class TreningService {
	
	@Context
	ServletContext ctx;
	

	public TreningService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("treningDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	PokretanjeProjekta.getInstance(contextPath);
			ctx.setAttribute("treningDAO", TreningDAO.getInstance());
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getTreninzi() {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		return dao.findAll();
	}
	
	@GET
	@Path("/getTreneri")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Korisnik> getTreneriZaSportskiObjekat(@QueryParam("idSportskogObjekta") int idSportskogObjekta) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		return dao.getTreneriZaSportskiObjekat(idSportskogObjekta);
	}
	
	//treba izmeniti
	
	@GET
	@Path("/getSportsObject")
	@Produces(MediaType.APPLICATION_JSON)
	public SportskiObjekat getSportskiObjekat(@QueryParam("id") int id) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		return dao.findObjekat(id);
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public SportskiObjekat save(SportskiObjekat sportskiObjekat) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		return dao.save(sportskiObjekat);
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public SportskiObjekat update(SportskiObjekat sportskiObjekat) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		return dao.update(sportskiObjekat);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getProducts(@PathParam("id") String id) { //izmeni u int
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		dao.delete(id);
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportskiObjekat> search(@QueryParam("searchIme") String searchIme, @QueryParam("searchTip") String searchTip,
			@QueryParam("searchLokacija") String searchLokacija, @QueryParam("searchOcena") String searchOcena) {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		return dao.search(searchIme, searchTip, searchLokacija, searchOcena);
	}
}
