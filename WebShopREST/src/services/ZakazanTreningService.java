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

import beans.Korisnik;
import beans.SportskiObjekat;
import beans.Trening;
import beans.ZakazanTrening;
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;
import dao.ZakazanTreningDAO;
import dto.KorisnikDTO;
import dto.ZakazanTreningDTO;

import dto.TreningDTO;
import utils.PokretanjeProjekta;

import javax.ws.rs.Path;

@Path("/zakazanTrening")
public class ZakazanTreningService {
	@Context
	ServletContext ctx;
	

	public ZakazanTreningService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("zakazanTreningDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	PokretanjeProjekta.getInstance(contextPath);
			ctx.setAttribute("zakazanTreningDAO", ZakazanTreningDAO.getInstance());
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getTreninzi() {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		return dao.findAll();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(ZakazanTreningDTO zakazanTreningDTO, @Context HttpServletRequest request) {
		ZakazanTreningDAO dao = (ZakazanTreningDAO) ctx.getAttribute("zakazanTreningDAO");
		ZakazanTrening zakazanTrening = new ZakazanTrening();
		zakazanTrening.setIntID(zakazanTreningDTO.getIntID());
		zakazanTrening.setTerminTreninga(zakazanTreningDTO.getTerminTreninga());
		zakazanTrening.setStatusTreninga(zakazanTreningDTO.getStatusTreninga());
		Korisnik kupac = (Korisnik)request.getSession().getAttribute("user");
		zakazanTrening.setKupac(kupac);
		Korisnik trener = KorisnikDAO.getInstance().find(zakazanTreningDTO.getTrenerIntId());
		zakazanTrening.setTrener(trener);
		SportskiObjekat objekat = SportskiObjekatDAO.getInstance().findObjekat(zakazanTreningDTO.getObjekatIntId());
		zakazanTrening.setObjekatGdePripada(objekat);
		
		dao.save(zakazanTrening);
			
		return Response.status(200).build();
	}	
	

}
