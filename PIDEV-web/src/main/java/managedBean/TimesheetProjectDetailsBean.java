package managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.Timesheet.ProjectServiceLocal;
import Service.Timesheet.TicketServiceLocal;
import entity.Project;
import entity.StatusTicket;
import entity.Ticket;

@ManagedBean
@SessionScoped
public class TimesheetProjectDetailsBean {

	@EJB
	private ProjectServiceLocal projectServiceLocal;
	@EJB
	private TicketServiceLocal ticketServiceLocal;

	private Ticket ticket = new Ticket();
	private Project detailsproject;
	private String startDateString = "";
	private String endDateString = "";
	private int userid;
	public TimesheetProjectDetailsBean() {

	}

	public String createTIcket() {

		String navigateTo = "/Timesheet_Web/detailsproject?faces-redirect=true";
		Date endDate = new Date();
		Date startDate = new Date();
		try {
			String pattern = "dd-MM-yyyy";
			startDate = new SimpleDateFormat(pattern).parse(startDateString);
			endDate = new SimpleDateFormat(pattern).parse(endDateString);
		} catch (Exception e) {
			// TODO: handle exception`
		}
		ticket.setStartDate(startDate);
		ticket.setEndDate(endDate);
		ticket.setStatus(StatusTicket.ToDo);
		ticket.setProject(detailsproject);
		ticketServiceLocal.create(ticket);
		
		ticket = new Ticket();
		return navigateTo;

	}

	public String goDetailsProject(Project p) {
		String navigateTo = "/Timesheet_Web/detailsproject?faces-redirect=true";
		detailsproject = p;

		return navigateTo;

	}

	public Project getDetailsproject() {
		return detailsproject;
	}

	public void setDetailsproject(Project detailsproject) {
		this.detailsproject = detailsproject;
	}

	public List<Ticket> getTodos() {
		List<Ticket> vb = ticketServiceLocal.getTicketsByProjectByStaus(detailsproject.getId(), StatusTicket.ToDo);
		System.out.println(vb.size());
		return vb;
	}

	public List<Ticket> getDones() {
		List<Ticket> vb = ticketServiceLocal.getTicketsByProjectByStaus(detailsproject.getId(), StatusTicket.Done);
		System.out.println(vb.size());

		return vb;

	}

	public List<Ticket> getDoings() {
		List<Ticket> vb = ticketServiceLocal.getTicketsByProjectByStaus(detailsproject.getId(),
				StatusTicket.InProgress);
		System.out.println(vb.size());

		return vb;

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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	

}
