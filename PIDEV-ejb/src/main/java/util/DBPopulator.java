package util;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import Service.UtilisateurServiceLocal;
import Service.Timesheet.ProjectServiceLocal;
import Service.Timesheet.TicketServiceLocal;
import entity.Employe;
import entity.Project;
import entity.StatusTicket;
import entity.Ticket;

@Singleton
@Startup
public class DBPopulator {

	@EJB
	private TicketServiceLocal ticketServiceLocal;
	@EJB
	private ProjectServiceLocal projectServiceLocal;
	@EJB
	private UtilisateurServiceLocal utilisateurServiceLocal;

	public DBPopulator() {
	}

	@PostConstruct
	public void init() {
		System.out.println("Application Strated ....");
		// getProject();
		// createTicket();
		// assignAndUpdate();
		searchtiketproject();
	}

	public void searchtiketproject() {
		List<Ticket> list = ticketServiceLocal.getTicketsByProject(4);

		for (Ticket t : list) {
			System.out.println(t.getId() + " , " + t.getName());
		}

	}

	public void assignAndUpdate() {
		ticketServiceLocal.assignTicketToEmploye(1, 2);
		ticketServiceLocal.updateStatus(1, StatusTicket.Done);
		System.out.println("heyy");
	}

	public void getProject() {
		ticketServiceLocal.addTicketToProject(3, 4);
	}

	public void updateTicket() {
		Ticket t1 = ticketServiceLocal.getById(1);
		System.out.println(t1.getName());
		t1.setDescription("desc updated 2");
		t1.setName("ticket1 update");
		ticketServiceLocal.update(t1);
		System.out.println("ticket 1 updated ...");
	}

	public void createTicket() {
		Ticket t = new Ticket();
		t.setName("tnada");
		t.setDescription("desc1 tnada");
		t.setStartDate(new Date());
		t.setEndDate(new Date());
		ticketServiceLocal.create(t);
		System.out.println("ticket created ...");
	}

	public void getTickets() {
		List<Ticket> list = ticketServiceLocal.findAll();
		System.out.println("ticket list ...");
		for (Ticket t : list) {
			System.out.println(t.getId() + " , " + t.getName());
		}
	}

	public void createProject() {
		Project p1 = new Project();
		p1.setName(" num 1");
		p1.setDescription("desc1 du projet 1");
		p1.setStartDate(new Date());
		p1.setEndDate(new Date());
		projectServiceLocal.createp(p1);
		System.out.println("project num 1 created :D ");
	}

	public void updateProject() {
		Project p1 = projectServiceLocal.getById(1);
		System.out.println(p1.getName());
		p1.setDescription("description project 1 updated");
		p1.setName("projct num 1 updated");
		projectServiceLocal.updatep(p1);
		System.out.println("Project 1 updated successufly :D ");
	}

	public void deleteproject() {
		Project p1 = projectServiceLocal.getById(2);
		System.out.println(p1.getName());
		projectServiceLocal.deletep(2);
		System.out.println("prject deleted :D ");
	}

	public void deleteticket() {
		// Ticket t1=ticketServiceLocal.getById(2);
		ticketServiceLocal.delete(2);
		System.out.println("ticket deleted :D ");
	}

}
