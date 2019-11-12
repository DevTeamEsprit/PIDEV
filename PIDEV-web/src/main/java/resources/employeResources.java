package resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entity.Employe;
import service.ServiceManager;

@Path("getsionemploye")
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
	
}
