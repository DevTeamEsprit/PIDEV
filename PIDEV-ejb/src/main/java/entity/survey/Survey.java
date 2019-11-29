package entity.survey;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import entity.Employe;
import entity.Manager;

@Entity
public class Survey implements Serializable {

	public Survey() {
		// TODO Auto-generated constructor stub
	}

	@EmbeddedId
	private SurveyId pk;
	@ManyToOne
	@JoinColumn(name="empId",insertable=false,updatable=false)
	private Employe employe;
	
	@ManyToOne
	@JoinColumn(name="manId",insertable=false,updatable=false)
	private Manager manager;
	
    private Date date;
	
	private int duree;
	
	private boolean state;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public SurveyId getPk() {
		return pk;
	}

	public void setPk(SurveyId pk) {
		this.pk = pk;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	

}
