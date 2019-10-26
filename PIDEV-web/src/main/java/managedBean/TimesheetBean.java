package managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import Service.Timesheet.TicketServiceLocal;
import entity.Ticket;

@ManagedBean
@RequestScoped
public class TimesheetBean {

	@EJB
	private TicketServiceLocal ticketServiceLocal;

	private List<Ticket> tickets;
	private Ticket ticket;
	private Ticket updateTicket;
	private Ticket detailsTicket;
	private String startDateString = "";
	private String endDateString = "";

	public TimesheetBean() {
	}

	@PostConstruct
	public void init() {
		tickets = ticketServiceLocal.findAll();
		ticket = new Ticket();
	}

	public String doDeleteTicket(int id ) {
		String navigateTo = "/Timesheet_Web/tickets?faces-redirect=true";
		ticketServiceLocal.delete(id);
		return navigateTo;
	}
	
	
	public String doCreateTicket() {
		String navigateTo = "/Timesheet_Web/tickets?faces-redirect=true";
		Date endDate = new Date();
		Date startDate = new Date();
		try {
			String pattern = "dd-MM-yyyy";
			startDate=new SimpleDateFormat(pattern).parse(startDateString);  
			endDate=new SimpleDateFormat(pattern).parse(endDateString); 
		} catch (Exception e) {
			// TODO: handle exception`
		}
		ticket.setStartDate(startDate);
		ticket.setEndDate(endDate);
		ticketServiceLocal.create(ticket);
		ticket = new Ticket();
		return navigateTo;
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

	public List<Ticket> getTickets() {
		return tickets;

	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Ticket getUpdateTicket() {
		return updateTicket;
	}

	public void setUpdateTicket(Ticket updateTicket) {
		this.updateTicket = updateTicket;
	}


}
