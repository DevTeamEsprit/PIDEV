package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Publication implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	
	public Publication() {
		super();
	}


	public Publication(Long id, String description, Date dateCreation) {
		super();
		this.id = id;
		this.description = description;
		this.dateCreation = dateCreation;
	}


	public Publication(String description, Date dateCreation) {
		super();
		this.description = description;
		this.dateCreation = dateCreation;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	@Override
	public String toString() {
		return "Publication [id=" + id + ", description=" + description + ", dateCreation=" + dateCreation + "]";
	}
	
	
	
	
}
