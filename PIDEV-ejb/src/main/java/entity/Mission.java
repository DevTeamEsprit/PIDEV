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
public class Mission implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String localisation;
	private int Idemp;
	@Temporal(TemporalType.DATE)
	private Date date;
	private int Duration;
	private boolean stat;
	private boolean resultat;
	public Mission(int id, String localisation, int idemp, java.util.Date date, int duration, boolean stat,
			boolean resultat) {
		super();
		this.id = id;
		this.localisation = localisation;
		Idemp = idemp;
		date = date;
		Duration = duration;
		this.stat = stat;
		this.resultat = resultat;
	}
	public Mission() {
		super();
		// TODO Auto-generated constructor stub
	}
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
		return Idemp;
	}
	public void setIdemp(int idemp) {
		Idemp = idemp;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		date = date;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public boolean isStat() {
		return stat;
	}
	public void setStat(boolean Stat) {
		stat = Stat;
	}
	public boolean isResultat() {
		return resultat;
	}
	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}
	@Override
	public String toString() {
		return "Mission [id=" + id + ", localisation=" + localisation + ", Idemp=" + Idemp + ", Date=" + date
				+ ", Duration=" + Duration + ", stat=" + stat + ", resultat=" + resultat + "]";
	}

	
	}

