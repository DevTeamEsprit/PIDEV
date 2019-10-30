package entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Formation implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ; 
	private String decription ;
	private Date dateformation;
	private int nbrjour;
	
	
	
	@OneToMany(mappedBy = "formation", fetch=FetchType.EAGER)
	private List<DemandeFormation> lstdemande=new ArrayList<>();
	
	
	
	
	public Formation() {
		super();
	}
	public Formation(String decription, Date dateformation, int nbrjour) {
		super();
		this.decription = decription;
		this.dateformation = dateformation;
		this.nbrjour = nbrjour;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public Date getDateformation() {
		return dateformation;
	}
	public void setDateformation(Date dateformation) {
		this.dateformation = dateformation;
	}
	public int getNbrjour() {
		return nbrjour;
	}
	public void setNbrjour(int nbrjour) {
		this.nbrjour = nbrjour;
	}
	public List<DemandeFormation> getLstdemande() {
		return lstdemande;
	}
	public void setLstdemande(List<DemandeFormation> lstdemande) {
		this.lstdemande = lstdemande;
	}
	@Override
	public String toString() {
		return "Formation [id=" + id + ", decription=" + decription + ", dateformation=" + dateformation + ", nbrjour="
				+ nbrjour + "]";
	}
}