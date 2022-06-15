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
import dao.UserDAO;

@Path("/user")
public class LoginService {
	
	@Context
	ServletContext ctx;
	
	public LoginService() {
		
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Korisnik user, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		Korisnik loggedUser = userDao.find(user.getUsername(), user.getPassword());
		if (loggedUser != null) {
			return Response.status(400).entity("Invalid username and/or password").build();
		}
		request.getSession().setAttribute("user", loggedUser);
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

		if (userToRegister.getUsername() == null || userToRegister.getPassword() == null
				|| userToRegister.getUsername().equals("") || userToRegister.getPassword().equals("")) {
			return Response.status(400).entity("Username i password su obavezna polja.").build();
		}
		
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");

		if (userDao.checkUsername(userToRegister.getUsername()) != null) {
			return Response.status(400).entity("Username koji ste uneli vec je zauzet.").build();
		} else {
			userDao.addUser(userToRegister);
			return Response.status(200).build();
		}
	}
}
