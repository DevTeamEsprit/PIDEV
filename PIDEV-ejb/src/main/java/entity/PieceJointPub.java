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
public class PieceJointPub implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreation;
	
	
	private Publication publication;
	
	
	public PieceJointPub() {
		super();
	}


	public PieceJointPub(String description, Date datecreation) {
		super();
		this.description = description;
		this.datecreation = datecreation;
	}


	public PieceJointPub(long id, String description, Date datecreation) {
		super();
		this.id = id;
		this.description = description;
		this.datecreation = datecreation;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDatecreation() {
		return datecreation;
	}


	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}


	@Override
	public String toString() {
		return "PieceJointPub [id=" + id + ", description=" + description + ", datecreation=" + datecreation + "]";
	}
	
	
}
