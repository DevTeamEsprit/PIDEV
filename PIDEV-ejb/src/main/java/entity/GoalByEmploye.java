package entity;

import java.io.Serializable;

import javax.persistence.*;



@Entity
public class GoalByEmploye implements Serializable{
	@EmbeddedId
	private GoalByEmployeId pk;
	@ManyToOne
	@JoinColumn(name="employeId",insertable=false,updatable=false)
	private Employe employe;
	
	@ManyToOne
	@JoinColumn(name="GoalId",insertable=false,updatable=false)
	private Goal goal;
	
	@ManyToOne
	private EvaluationSheet evaluationSheet;

	public EvaluationSheet getEvaluationSheet() {
		return evaluationSheet;
	}

	public void setEvaluationSheet(EvaluationSheet evaluationSheet) {
		this.evaluationSheet = evaluationSheet;
	}

	public GoalByEmploye(GoalByEmployeId pk) {
		super();
		this.pk = pk;
	}

	public GoalByEmploye() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoalByEmployeId getPk() {
		return pk;
	}

	public void setPk(GoalByEmployeId pk) {
		this.pk = pk;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	

}
