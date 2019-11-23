package entity.survey;

import java.io.Serializable;

import javax.persistence.*;

import entity.Employe;
import entity.Manager;

@Entity
public class SurveyEmploye implements Serializable{

	public SurveyEmploye() {
		// TODO Auto-generated constructor stub
	}


	@EmbeddedId
	private SurveyEmployeId pk;
	@ManyToOne
	@JoinColumn(name="EmpId",insertable=false,updatable=false)
	private Employe employe;
	
	@ManyToOne
	@JoinColumn(name="QuestId",insertable=false,updatable=false)
	private SurveyQuestion question;
	
	private SurveyComment appreciation;

	
	public SurveyEmploye(SurveyEmployeId pk, Employe employe, SurveyQuestion question, SurveyComment appreciation) {
		super();
		this.pk = pk;
		this.employe = employe;
		this.question = question;
		this.appreciation = appreciation;
	}

	public SurveyEmployeId getPk() {
		return pk;
	}

	public void setPk(SurveyEmployeId pk) {
		this.pk = pk;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public SurveyQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SurveyQuestion question) {
		this.question = question;
	}

	public SurveyComment getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(SurveyComment appreciation) {
		this.appreciation = appreciation;
	}
	
	
	
}
