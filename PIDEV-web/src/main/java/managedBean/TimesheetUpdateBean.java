package managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.Timesheet.TicketServiceLocal;
import entity.Ticket;

@ManagedBean
@SessionScoped
public class TimesheetUpdateBean {

	@EJB
	private TicketServiceLocal ticketServiceLocal;

	private List<Ticket> tickets;
	private Ticket updateTicket;
	private String startDateString = "";
	private String endDateString = "";
	private String error= "" ;
	public TimesheetUpdateBean() {
	}

	@PostConstruct
	public void init() {
		updateTicket = new Ticket();
	}	

	public String goUpdateTicket(Ticket ticket ) {
		String navigateTo = "/Timesheet_Web/update?faces-redirect=true";
		error = "";
		updateTicket = ticket;
		startDateString = updateTicket.getStartDate().toString();
		endDateString = updateTicket.getEndDate().toString();

		return navigateTo;
	}
	
	public String doUpdateTicket() {
		String navigateTo = "/Timesheet_Web/tickets?faces-redirect=true";
		String updateUrl = "/Timesheet_Web/update?faces-redirect=true";

		Date endDate = new Date();
		Date startDate = new Date();
		try {
			// cast string to date
			String pattern = "dd-MM-yyyy";
			startDate=new SimpleDateFormat(pattern).parse(startDateString);  
			endDate=new SimpleDateFormat(pattern).parse(endDateString);  

		} catch (Exception e) {
			// TODO: handle exception
			error = "Date invalide";
			return updateUrl;
		}
		updateTicket.setStartDate(startDate);
		updateTicket.setEndDate(endDate);	
		ticketServiceLocal.update(updateTicket);
		error = "";
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

	public Ticket getUpdateTicket() {
		return updateTicket;
	}

	public void setUpdateTicket(Ticket updateTicket) {
		this.updateTicket = updateTicket;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


}
