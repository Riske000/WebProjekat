package services;

import java.time.LocalDate;
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

import beans.Clanarina;
import beans.Korisnik;
import beans.SportskiObjekat;
import beans.Trening;
import dao.ClanarinaDAO;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;
import dto.ClanarinaDTO;
import dto.KorisnikDTO;

import dto.TreningDTO;
import utils.DateHelper;
import utils.PokretanjeProjekta;

@Path("/clanarina")
public class ClanarinaService {

	@Context
	ServletContext ctx;
	

	public ClanarinaService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("clanarinaDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	PokretanjeProjekta.getInstance(contextPath);
			ctx.setAttribute("clanarinaDAO", ClanarinaDAO.getInstance());
		}
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ClanarinaDTO save(Clanarina clanarina,@Context HttpServletRequest request) {
		ClanarinaDAO dao = (ClanarinaDAO) ctx.getAttribute("clanarinaDAO");
		Korisnik logged = (Korisnik) request.getSession().getAttribute("user");
		if(logged == null) {
			return null;
		}
		logged.setClanarina(clanarina);
		clanarina.setKupac(logged);
		clanarina = dao.novaClanarina(clanarina);
		KorisnikDAO.getInstance().update(logged);
		return new ClanarinaDTO(clanarina);
	}
	
	@POST
	@Path("/setSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSelected(Clanarina clanarina, @Context HttpServletRequest request) {
		request.getSession().setAttribute("izabranaClanarina", clanarina);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ClanarinaDTO getSelected( @Context HttpServletRequest request) {
		Clanarina clanarina = (Clanarina)request.getSession().getAttribute("izabranaClanarina");
		if(clanarina == null) {
			return null;
		}
		clanarina.setDatumPlacanja(DateHelper.dateToString(LocalDate.now()));
		return new ClanarinaDTO(clanarina);
	}
}
