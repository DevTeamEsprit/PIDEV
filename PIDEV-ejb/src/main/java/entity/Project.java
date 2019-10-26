package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	@OneToMany(mappedBy = "project")
	private List<Ticket> tickets;

	public List<Ticket> getTickets() {
		return tickets;
	}
	
	public void addTicket(Ticket t) {
		if(tickets == null) {
			tickets = new ArrayList<Ticket>();
		}
		if(!tickets.contains(t)) {
			tickets.add(t);
		}		
	}
	
	public void removeTicket(Ticket t) {	
		if(tickets != null && tickets.contains(t)) {
			tickets.remove(t);
		}
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Project(int id, String name, String description, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Project() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
