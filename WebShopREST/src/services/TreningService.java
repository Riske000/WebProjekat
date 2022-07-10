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
import dao.KorisnikDAO;
import dao.SportskiObjekatDAO;
import dao.TreningDAO;
import dto.KorisnikDTO;

import dto.TreningDTO;
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
	public Collection<KorisnikDTO> getTreneriZaSportskiObjekat(@QueryParam("idSportskogObjekta") int idSportskogObjekta) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		Collection<Korisnik> treneri = dao.getTreneriZaSportskiObjekat(idSportskogObjekta);
		ArrayList<KorisnikDTO> treneriDTO = new ArrayList<KorisnikDTO>();
		for(Korisnik t : treneri) {
			treneriDTO.add(new KorisnikDTO(t));
		}
		return treneriDTO;
	}
	
	@GET
	@Path("/getTreninzi")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TreningDTO> getTreninziZaSportskiObjekat(@QueryParam("idSportskogObjekta") int idSportskogObjekta) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		ArrayList<Trening> treninzi = dao.getTreninziZaSportskiObjekat(idSportskogObjekta);
		ArrayList<TreningDTO> treninziDTO = new ArrayList<TreningDTO>();
		for(Trening t : treninzi) {
			treninziDTO.add(new TreningDTO(t));
		}
		return treninziDTO;
	}
	
	@GET
	@Path("/getPersonalTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TreningDTO> getPersonalniTreninziZaTrenera(@QueryParam("idKorisnika") int idKorisnika) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		ArrayList<Trening> personalniTreninzi = dao.getPersonalniTreninziZaTrenera(idKorisnika);
		ArrayList<TreningDTO> personalniTreninziDTO = new ArrayList<TreningDTO>();
		for(Trening t : personalniTreninzi) {
			personalniTreninziDTO.add(new TreningDTO(t));
		}
		return personalniTreninziDTO;
	}
	
	@GET
	@Path("/getGroupTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TreningDTO> getGrupniTreninziZaTrenera(@QueryParam("idKorisnika") int idKorisnika) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		ArrayList<Trening> grupniTreninzi = dao.getGrupniTreninziZaTrenera(idKorisnika);
		ArrayList<TreningDTO> grupniTreninziDTO = new ArrayList<TreningDTO>();
		for(Trening t : grupniTreninzi) {
			grupniTreninziDTO.add(new TreningDTO(t));
		}
		return grupniTreninziDTO;
	}
	
	
	@PUT
	@Path("/cancelTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public Response OtkaziTrening(int id) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		//return dao.update(sportskiObjekat);
		return null;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void changeOne(TreningDTO treningDTO) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		Trening trening = new Trening();
		trening.setIntId(treningDTO.getIntId());
		trening.setNaziv(treningDTO.getNaziv());
		trening.setTipTreninga(treningDTO.getTipTreninga());
		trening.setObjekatGdePripada(treningDTO.getObjekatGdePripada());
		trening.setTrajanje(treningDTO.getTrajanje());
		trening.setOpis(treningDTO.getOpis());
		trening.setSlika(treningDTO.getSlika());
		Korisnik trener = KorisnikDAO.getInstance().find(treningDTO.getTrenerIntId());
		trening.setTrener(trener);
		
		dao.update(trening);
	}
	
	@POST
	@Path("/setSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSelected(TreningDTO treningDTO, @Context HttpServletRequest request) {
		Trening trening = TreningDAO.getInstance().findTrening(treningDTO.getIntId());
		request.getSession().setAttribute("izabraniTrening", trening);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TreningDTO getSelected( @Context HttpServletRequest request) {
		Trening trening = (Trening)request.getSession().getAttribute("izabraniTrening");
		return new TreningDTO(trening);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void save(TreningDTO treningDTO, @Context HttpServletRequest request) {
		TreningDAO dao = (TreningDAO) ctx.getAttribute("treningDAO");
		Trening trening = new Trening();
		trening.setIntId(treningDTO.getIntId());
		trening.setNaziv(treningDTO.getNaziv());
		trening.setTipTreninga(treningDTO.getTipTreninga());
		Korisnik menadzer = (Korisnik)request.getSession().getAttribute("user");
		SportskiObjekat objekatGdePripada = menadzer.getSportskiObjekat(); // proveri da li mora preko SportskiObjekatDAO
		trening.setObjekatGdePripada(objekatGdePripada);
		trening.setTrajanje(treningDTO.getTrajanje());
		trening.setOpis(treningDTO.getOpis());
		trening.setSlika(treningDTO.getSlika());
		Korisnik trener = KorisnikDAO.getInstance().find(treningDTO.getTrenerIntId());
		trening.setTrener(trener);
		
		dao.save(trening);
	}
	
}
