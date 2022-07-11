package services;

import java.time.LocalDateTime;
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

import beans.IstorijaTreninga;
import beans.Korisnik;
import beans.SportskiObjekat;
import beans.Trening;
import dao.IstorijaTreningaDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;
import dto.IstorijaTreningaDTO;
import utils.DateTimeHelper;
import utils.PokretanjeProjekta;

@Path("/istorijaTreninga")
public class IstorijaTreningaService {
	
	@Context
	ServletContext ctx;
	

	public IstorijaTreningaService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("istorijaTreningaDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	PokretanjeProjekta.getInstance(contextPath);
			ctx.setAttribute("istorijaTreningaDAO", IstorijaTreningaDAO.getInstance());
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<IstorijaTreninga> findAll() {
		IstorijaTreningaDAO dao = (IstorijaTreningaDAO) ctx.getAttribute("istorijaTreningaDAO");
		return dao.findAll();
	}
	
	@GET
	@Path("/getITforUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<IstorijaTreningaDTO> getIstorijaTreningaZaKorisnika(@QueryParam("idKorisnika") int idKorisnika) {
		IstorijaTreningaDAO dao = (IstorijaTreningaDAO) ctx.getAttribute("istorijaTreningaDAO");
		ArrayList<IstorijaTreninga> treninzi = (ArrayList<IstorijaTreninga>) dao.getIstorijaTreningaZaKorisnika(idKorisnika);
		ArrayList<IstorijaTreningaDTO> treninziDTO = new ArrayList<IstorijaTreningaDTO>();
		for(IstorijaTreninga istorija : treninzi) {
			treninziDTO.add(new IstorijaTreningaDTO(istorija));
		}
		return treninziDTO;
	}
	
	@POST
	@Path("/cekirajSe")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public IstorijaTreninga napravi(Trening trening, @Context HttpServletRequest request) {
		Korisnik logovani = (Korisnik) request.getSession().getAttribute("user");
		IstorijaTreningaDAO dao = (IstorijaTreningaDAO) ctx.getAttribute("istorijaTreningaDAO");
		
		return dao.check(trening, logovani);
	}
	
	//treba izmeniti
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<IstorijaTreninga> search(@QueryParam("searchObjekat") String searchObjekat, @QueryParam("pocetno") String pocetno, 
			@QueryParam("krajnje") String krajnje, @Context HttpServletRequest request){
		
		Korisnik logovani = (Korisnik) request.getSession().getAttribute("user");
		LocalDateTime p = DateTimeHelper.stringToDateTime(pocetno);
		LocalDateTime k = DateTimeHelper.stringToDateTime(krajnje);
		
		IstorijaTreningaDAO dao = (IstorijaTreningaDAO) ctx.getAttribute("istorijaTreningaDAO");
		
		return dao.search(searchObjekat, p, k, logovani);
	}
}
