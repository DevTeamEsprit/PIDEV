<<<<<<< HEAD
package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Goal implements Serializable{
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private GoalType type;
	private String text;
	
	
	public Goal(int id, GoalType type, String text, Evaluation evaluation) {
		super();
		this.id = id;
		this.type = type;
		this.text = text;
		this.evaluation = evaluation;
	}
	public Goal() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	private Evaluation evaluation;
	public Evaluation getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public GoalType getType() {
		return type;
	}
	public void setType(GoalType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
=======
package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Goal implements Serializable{
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID_Goal;
	private GoalType type;
	private String text;
	private boolean state;
	@ManyToOne
	private Evaluation evaluation;
	public int getID_Goal() {
		return ID_Goal;
	}
	public void setID_Goal(int iD_Goal) {
		ID_Goal = iD_Goal;
	}
	public GoalType getType() {
		return type;
	}
	public void setType(GoalType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Evaluation getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
}
>>>>>>> branch 'master' of https://github.com/DevTeamEsprit/PIDEV.git
