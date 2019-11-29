package resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

import Service.Timesheet.TicketServiceLocal;
import entity.Employe;
import entity.Project;
import entity.Ticket;


import Service.Timesheet.*;

@Path("gestionTicket")
@RequestScoped
public class ticketResources {

	@EJB
	private TicketServiceLocal ticketServiceLocal ;
	
	

	static List<Ticket> tickets = new ArrayList<Ticket>();
	public ticketResources() 
	{
		super();
 	}
	
	@GET
	@Path("/ticket")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response listTicket() {
		System.out.println("aaa");
		return Response.status(Response.Status.CREATED).entity(ticketServiceLocal.findAll()).build();
	}
	}
