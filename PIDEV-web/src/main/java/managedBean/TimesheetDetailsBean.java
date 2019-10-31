package managedBean;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import Service.UtilisateurServiceLocal;
import Service.Timesheet.TicketServiceLocal;
import entity.StatusTicket;
import entity.Ticket;

@ManagedBean
@SessionScoped
public class TimesheetDetailsBean {

	@EJB
	private TicketServiceLocal ticketServiceLocal;
	@EJB
	private UtilisateurServiceLocal utilisateurServiceLocal;

	private Ticket detailsTicket;
	private String startDateString = "";
	private String endDateString = "";
   
	public TimesheetDetailsBean() {
	}

	@PostConstruct
	public void init() {
		detailsTicket = new Ticket();
		
	}

	public int getProjectTicketsByStatus(int idProject, int type) {
		StatusTicket status = StatusTicket.ToDo;
		switch (type) {
		case 1:
			status = StatusTicket.ToDo;
			break;
		case 2:
			status = StatusTicket.InProgress;
			break;
		case 3:
			status = StatusTicket.Done;
			break;
		default:
			break;
		}
		
		return ticketServiceLocal.getTicketsByProjectByStaus(idProject, status).size();
	}

	public String goDetailsTicket(Ticket t) {
		String navigateTo = "/Timesheet_Web/detailsticket?faces-redirect=true";
		// System.out.println("status : "+t.getStatus().toString());
		detailsTicket = t;
		startDateString = detailsTicket.getStartDate().toString();
		endDateString = detailsTicket.getEndDate().toString();
		// ticketServiceLocal.detailsTicket(detailsTicket,id);
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
		return "/Timesheet_Web/detailsticket?faces-redirect=true";

	}

	public String doupdatestatus(Ticket ticket) {

		if (ticket.getStatus().equals(StatusTicket.ToDo)) {
			ticket.setStatus(StatusTicket.InProgress);
		}

		else if (ticket.getStatus().equals(StatusTicket.InProgress)) {
			ticket.setStatus(StatusTicket.Done);
		}

		ticketServiceLocal.update(ticket);
		return "/Timesheet_Web/detailsproject?faces-redirect=true";

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
