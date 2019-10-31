
package entity;

import java.io.Serializable;
import entity.GoalType;
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