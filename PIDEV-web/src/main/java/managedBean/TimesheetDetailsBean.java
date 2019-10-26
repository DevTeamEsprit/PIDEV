package managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.Timesheet.TicketServiceLocal;
import entity.Ticket;

@ManagedBean
@SessionScoped
public class TimesheetDetailsBean {

	@EJB
	private TicketServiceLocal ticketServiceLocal;

	private Ticket detailsTicket;
	private String startDateString ="";
	private String endDateString = "";
	public TimesheetDetailsBean() {
	}

	@PostConstruct
	public void init() {
		detailsTicket = new Ticket();
	}	
	
	

	public String goDetailsTicket(Ticket t) {
		String navigateTo = "/Timesheet_Web/detailsticket?faces-redirect=true";
		System.out.println("status : "+t.getStatus().toString());
		detailsTicket = t;
		startDateString = detailsTicket.getStartDate().toString();
		endDateString = detailsTicket.getEndDate().toString();
		//ticketServiceLocal.detailsTicket(detailsTicket,id);
		return navigateTo;
		
	}

	public Ticket getDetailsTicket() {
		return detailsTicket;
	}

	public void setDetailsTicket(Ticket detailsTicket) {
		this.detailsTicket = detailsTicket;
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
	
	
	


}
