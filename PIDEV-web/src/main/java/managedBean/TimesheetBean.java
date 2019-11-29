package managedBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

import Service.Timesheet.ProjectServiceLocal;
import Service.Timesheet.TicketServiceLocal;
import entity.Employe;
import entity.Project;
import entity.StatusTicket;
import entity.Ticket;

@ManagedBean
@RequestScoped
public class TimesheetBean {

	@EJB
	private TicketServiceLocal ticketServiceLocal;
	@EJB
	private ProjectServiceLocal projectServiceLocal;

	private List<Ticket> tickets;
	private List<Project> projects;
	private List<Employe> listemployees;
	private Ticket ticket;
	private Project project;
	private Ticket updateTicket;
	private Ticket detailsTicket;
	private String startDateString = "";
	private String endDateString = "";
	private int idEmp;
	private TimelineModel model;
	private Date start;
	private Date end;

	public TimesheetBean() {

	}

	@PostConstruct
	public void init() {
		loadTimeLine();
		tickets = ticketServiceLocal.findAll();
		ticket = new Ticket();
		projects = projectServiceLocal.findAll();
		listemployees = ticketServiceLocal.getEmployes();
	}

	public String doDeleteTicket(int id) {
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
			startDate = new SimpleDateFormat(pattern).parse(startDateString);
			endDate = new SimpleDateFormat(pattern).parse(endDateString);
		} catch (Exception e) {
			// TODO: handle exception`
		}
		ticket.setStartDate(startDate);
		ticket.setEndDate(endDate);
		ticket.setStatus(StatusTicket.ToDo);
		
		Employe e = ticketServiceLocal.findemployeebyId(idEmp);
		ticket.setEmploye(e);
		
		ticketServiceLocal.create(ticket);
		ticket = new Ticket();
		return navigateTo;
	}

	public void loadTimeLine() {
		// set initial start / end dates for the axis of the timeline
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Date now = new Date();
		System.out.println("------------ start date ");

		cal.setTimeInMillis(now.getTime() - 4 * 60 * 60 * 1000);
		start = cal.getTime();

		cal.setTimeInMillis(now.getTime() + 8 * 60 * 60 * 1000);
		end = cal.getTime();

		// create timeline model
		model = new TimelineModel();

		for (Employe e : ticketServiceLocal.getEmployes()) {
			now = new Date();
			Date end = new Date(now.getTime() - 12 * 60 * 60 * 1000);
			int ide = (int) e.getId();
			for (Ticket t : ticketServiceLocal.getTicketsByEmployee(ide)) {
				Date start = t.getStartDate();

				end = t.getEndDate();

				String availability;
				if (t.getStatus().equals(StatusTicket.ToDo)) {
					availability = "Unavailable";
				} else if (t.getStatus().equals(StatusTicket.InProgress)) {
					availability = "Maybe";
				} else {
					availability = "Available";
				}
				String eventName = t.getName();
				String name = e.getNom() + " " + e.getPrenom();
				// create an event with content, start / end dates, editable flag, group name
				// and custom style class
				TimelineEvent event = new TimelineEvent(eventName.toString(), start, end, false, name,
						availability.toLowerCase());
				model.add(event);
			}

		}
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

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public TimelineModel getModel() {
		return model;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setModel(TimelineModel model) {
		this.model = model;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<Employe> getListemployees() {
		return listemployees;
	}

	public void setListemployees(List<Employe> listemployees) {
		this.listemployees = listemployees;
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

}
