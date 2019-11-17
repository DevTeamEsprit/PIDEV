package resources;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entity.Employe;
import entity.Publication;
import entity.Utilisateur;
import service.ServiceManager;

@Path("gestionEmploye")
public class employeResources {

	@Inject
	private ServiceManager serviceManager;

	public employeResources() {
		super();
 
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consulterEmploy(){
		return Response.status(Response.Status.CREATED).entity(this.serviceManager.listerEmploye()).build();
	}
	
	@GET
	@Path("/publication")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response listPub() {
		return Response.status(Response.Status.CREATED).entity(this.serviceManager.getPubs()).build();
	}
	
	@POST
	@Path("/publication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPub(Publication p) {
		p.setDateCreation(new Date());
		this.serviceManager.addPub(p);
		return Response.status(Response.Status.CREATED).entity(this.serviceManager.getPubs()).build();
	}
	
	
	@PUT
	@Path("/publication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public Response updatePub(Publication p) {
		this.serviceManager.updatePub(p);
		return Response.status(Response.Status.OK).entity(this.serviceManager.getPubs()).build();
	}

	@DELETE
	@Path("/publication/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response deletePub(@PathParam(value="id") long idPub ) {
		this.serviceManager.deletePub(idPub);
		return Response.status(Response.Status.OK).entity(this.serviceManager.getPubs()).build();
	}


	@DELETE
	@Path("/publication/commentaire/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response deleteCom(@PathParam(value="id") long idCom ) {
		this.serviceManager.deleteCom(idCom);
		return Response.status(Response.Status.OK).entity(this.serviceManager.getPubs()).build();
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)		
	public Response login(@QueryParam(value="login") String login , @QueryParam(value="pass") String pass) {
		System.out.println(login+"/"+pass);
		Utilisateur user=this.serviceManager.doLogin(login,this.serviceManager.MD5(pass));
		System.out.println(user);
		if(user!=null)
			return Response.status(Response.Status.OK).entity("mawjoud").build();
		return Response.status(Response.Status.EXPECTATION_FAILED).entity("mahouch mawjoud").build();
	}
}
