package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ticket implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private StatusTicket status;
	private Date startDate;
	private Date endDate;
	@ManyToOne
	private Project project; 
	@ManyToOne
	private Employe employe; 
	

	public Ticket(int id, String name, String description, StatusTicket status, Date startDate, Date endDate,
			Project project, Employe employe) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.project = project;
		this.employe = employe;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public Ticket() {
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public StatusTicket getStatus() {
		return status;
	}
	public void setStatus(StatusTicket status) {
		this.status = status;
	}
	 
	
	
	
	

}
