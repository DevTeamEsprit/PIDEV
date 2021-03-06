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

import entity.Commentaire;
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
	
	// web service publication ajout+delete + lister + getpub
	@GET
	@Path("/publication")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response listPub() {
		return Response.status(Response.Status.CREATED).entity(this.serviceManager.getPubs()).build();
	}

	
	@GET
	@Path("/publication/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getPubById(@PathParam(value="id") long idPub ) {
	
		return Response.status(Response.Status.CREATED).entity(this.serviceManager.getPubById(idPub)).build();
	}
		
	
	@GET
	@Path("/publication/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getPubByuser(@PathParam(value="id") long iduser ) {
		Employe emp = (Employe) this.serviceManager.getUser(iduser);
		return Response.status(Response.Status.CREATED).entity(this.serviceManager.getPubsUser(emp)).build();
	}
	
	
	@POST
	@Path("/publication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPub(Publication p) {
	
		p.setDateCreation(new Date());
		this.serviceManager.addPub(p);
		return Response.status(Response.Status.CREATED).entity("ajouter avec succée").build();
	}

	@DELETE
	@Path("/publication/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response deletePub(@PathParam(value="id") long idPub ) {
		this.serviceManager.deletePub(idPub);
		return Response.status(Response.Status.OK).entity(this.serviceManager.getPubs()).build();
	}
	
	/*
	@PUT
	@Path("/publication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public Response updatePub(Publication p) {
		this.serviceManager.updatePub(p);
		return Response.status(Response.Status.OK).entity("modifier avec succée").build();
	}*/


	/*
	@GET
	@Path("/publication/commentaire/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getlstCom(@PathParam(value="id") long idpub ) {
	 
		return Response.status(Response.Status.OK).entity(this.serviceManager.getComPuB(idpub)).build();
	}
	*/
	
	@POST
	@Path("/publication/commentaire/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPubCom(Commentaire c,@PathParam(value="id") long idpub) {
		c.setDateCreation(new Date());
		c.setPub(this.serviceManager.getPubById(idpub));
		this.serviceManager.addCom(c);
		return Response.status(Response.Status.CREATED).entity("ajouter avec succée").build();
	}
	
	@DELETE
	@Path("/publication/commentaire/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response deleteCom(@PathParam(value="id") long idCom ) {
		this.serviceManager.deleteCom(idCom);
		return Response.status(Response.Status.OK).entity("supprimer avec succée").build();
	}
	
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)		
	public Response login(@QueryParam(value="login") String login , @QueryParam(value="pass") String pass) {
		Utilisateur user=this.serviceManager.doLogin(login,this.serviceManager.MD5(pass));
	//	System.out.println(user);
		if(user!=null)
			return Response.status(Response.Status.OK).entity(user).build();
		return Response.status(Response.Status.EXPECTATION_FAILED).entity("mahouch mawjoud").build();
	}
}
