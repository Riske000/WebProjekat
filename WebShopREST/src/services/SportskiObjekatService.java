package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.Response;

import beans.SportskiObjekat;
import dao.SportskiObjekatDAO;
import utils.PokretanjeProjekta;

@Path("/sportskiObjekti")
public class SportskiObjekatService {
	
	@Context
	ServletContext ctx;
	

	public SportskiObjekatService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("sportskiObjekatDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	PokretanjeProjekta.getInstance(contextPath);
			ctx.setAttribute("sportskiObjekatDAO", SportskiObjekatDAO.getInstance());
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportskiObjekat> getSportskiObjekti() {
		SportskiObjekatDAO dao = (SportskiObjekatDAO) ctx.getAttribute("sportskiObjekatDAO");
		return dao.findAll();
	}
	
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
	
	@POST
	@Path("/setSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSelected(SportskiObjekat obj, @Context HttpServletRequest request) {
		SportskiObjekat objekat = SportskiObjekatDAO.getInstance().findObjekat(obj.getIntId());
		request.getSession().setAttribute("selected", objekat);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportskiObjekat getSelected( @Context HttpServletRequest request) {
		SportskiObjekat objekat = (SportskiObjekat)request.getSession().getAttribute("selected");
		return objekat;
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
