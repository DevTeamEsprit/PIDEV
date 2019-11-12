package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Publication implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	@ManyToOne 
	@JoinColumn(name="id_user")
	private Utilisateur user;
	
	
	@OneToMany(mappedBy="pub" , cascade= {CascadeType.REMOVE} , fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Commentaire> lstComm = new ArrayList<>();
	
	public Publication() {
		super();
	}


	public List<Commentaire> getLstComm() {
		return lstComm;
	}


	public void setLstComm(List<Commentaire> lstComm) {
		this.lstComm = lstComm;
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
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
		return "Publication [id=" + id + ", description=" + description + ", dateCreation=" + dateCreation + ", user="
				+ user + ", lstComm=" + lstComm + "]";
	}


	 
	
	
	
	
}
