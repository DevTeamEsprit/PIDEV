package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DemandeFormation implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date demande;
	@ManyToOne 
	@JoinColumn(name="id_formation")
	private Formation formation;
	
	@ManyToOne 
	@JoinColumn(name="id_user")
	private Utilisateur user;
	
	
	
	public DemandeFormation() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDemande() {
		return demande;
	}
	public void setDemande(Date demande) {
		this.demande = demande;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public DemandeFormation(Date demande, Formation formation, Utilisateur user) {
		super();
		this.demande = demande;
		this.formation = formation;
		this.user = user;
	}
	@Override
	public String toString() {
		return "DemandeFormation [id=" + id + ", demande=" + demande + ", formation=" + formation + ", user=" + user
				+ "]";
	}
	
	
}
