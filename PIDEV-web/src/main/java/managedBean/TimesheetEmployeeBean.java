package managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import Service.Timesheet.ProjectServiceLocal;
import Service.Timesheet.TicketServiceLocal;
import entity.Project;
import entity.StatusTicket;
import entity.Ticket;


@ManagedBean
@SessionScoped
public class TimesheetEmployeeBean {

	@EJB
	private TicketServiceLocal ticketServiceLocal;

	private Ticket detailsTicket;
	private String startDateString = "";
	private String endDateString = "";
	private List<Ticket> tickets;
	// change user id : simulation log in
	private int currentUserConnected = 1;
	private List<Ticket> ticketsemployee;
	
	private Ticket ticket;

	public TimesheetEmployeeBean() {
	}

	@PostConstruct
	public void init() {
		setTickets(ticketServiceLocal.getTicketsByEmployee(currentUserConnected));
	}

	public String goDetailsTicket(Ticket t) {
		String navigateTo = "/Timesheet_Web_Employee/detailsticket?faces-redirect=true";
		detailsTicket = t;
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk: "+t.getId());
		setStartDateString(detailsTicket.getStartDate().toString());
		setEndDateString(detailsTicket.getEndDate().toString());
		//ticketServiceLocal.detailsTicket(detailsTicket,id);
		return navigateTo;

	}
	
	public String doUpdateStatus(String type) {

		StatusTicket status = StatusTicket.ToDo;
		switch (type) {
		case "todo":
			status = StatusTicket.ToDo;
			break;
		case "progress":
			status = StatusTicket.InProgress;
			break;
		case "done":
			status = StatusTicket.Done;
			break;
		default:
			break;
		}
		detailsTicket.setStatus(status);
		ticketServiceLocal.update(detailsTicket);
		return "/Timesheet_Web_Employee/detailsticket?faces-redirect=true";

	}
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Ticket> getTicketsemployee() {
		return ticketsemployee;
	}

	public void setTicketsemployee(List<Ticket> ticketsemployee) {
		this.ticketsemployee = ticketsemployee;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public Ticket getDetailsTicket() {
		return detailsTicket;
	}

	public void setDetailsTicket(Ticket detailsTicket) {
		this.detailsTicket = detailsTicket;
	}
	
	


	

}
