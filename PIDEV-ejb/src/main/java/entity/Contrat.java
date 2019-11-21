package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

 

 

@Entity
public class Contrat implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reference;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;


	@Enumerated(EnumType.STRING)
	private TypeContrat typeContrat;
	private float salaire;
	
	@OneToOne(mappedBy="contrat")
	@JsonManagedReference
	private Utilisateur utilisatuer;

	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Contrat(int reference, Date dateDebut, TypeContrat typeContrat, float salaire, Utilisateur employe) {
		super();
		this.reference = reference;
		this.dateDebut = dateDebut;
		this.typeContrat = typeContrat;
		this.salaire = salaire;
		this.utilisatuer = employe;
	}


	public int getReference() {
		return reference;
	}


	public Contrat() {
		super();
	}


	public void setReference(int reference) {
		this.reference = reference;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public TypeContrat getTypeContrat() {
		return typeContrat;
	}


	public void setTypeContrat(TypeContrat typeContrat) {
		this.typeContrat = typeContrat;
	}


	public float getSalaire() {
		return salaire;
	}


	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}


	public Utilisateur getEmploye() {
		return  utilisatuer;
	}


	public void setEmploye(Utilisateur employe) {
		this.utilisatuer = employe;
	}


	@Override
	public String toString() {
		return "Contrat [reference=" + reference + ", dateDebut=" + dateDebut + ", typeContrat=" + typeContrat
				+ ", salaire=" + salaire + "]";
	}
	
 
	
	
}
