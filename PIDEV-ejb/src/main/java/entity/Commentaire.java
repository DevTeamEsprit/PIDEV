package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commentaire implements Serializable {

	@Override
	public String toString() {
		return "Commentaire [id=" + id + ", description=" + description + ", dateCreation=" + dateCreation + ", pub="
				+ pub + ", user=" + user + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@ManyToOne 
	@JoinColumn(name="id_pub")
	private Publication pub;
	
	@ManyToOne 
	@JoinColumn(name="id_user")
	private Utilisateur user;

	
	
	
	public Commentaire() {
		super();
	}

	public Commentaire(long id, String description, Publication pub, Utilisateur user) {
		super();
		this.id = id;
		this.description = description;
		this.pub = pub;
		this.user = user;
	}

	public Commentaire(String description, Publication pub, Utilisateur user) {
		super();
		this.description = description;
		this.pub = pub;
		this.user = user;
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

	public Publication getPub() {
		return pub;
	}

	public void setPub(Publication pub) {
		this.pub = pub;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	
}
