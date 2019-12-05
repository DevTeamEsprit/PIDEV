package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Mission implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String localisation;
	private int idemp;
	private Date date;
	private int duration;
	private boolean stat;
	@Enumerated(EnumType.STRING)
	private resultatMission resultat;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public int getIdemp() {
		return idemp;
	}

	public void setIdemp(int idemp) {
		this.idemp = idemp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public resultatMission getResultat() {
		return resultat;
	}

	public void setResultat(resultatMission resultat) {
		this.resultat = resultat;
	}

	public Mission() {
		super();
	}

	public Mission(int id, String localisation, int idemp, Date date, int duration, boolean stat,
			resultatMission resultat) {
		super();
		this.id = id;
		this.localisation = localisation;
		this.idemp = idemp;
		this.date = date;
		this.duration = duration;
		this.stat = stat;
		this.resultat = resultat;
	}

	public Mission(int id, String localisation, Date date, int duration) {
		super();
		this.id = id;
		this.localisation = localisation;
		this.date = date;
		this.duration = duration;
	}
	
	
	
	

	
	}

