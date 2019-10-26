
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class EvaluationSheet implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean status;
	private String evalsheetTitle;
	public EvaluationSheet(int id, boolean status, String evalsheetTitle, String appreciation) {
		super();
		this.id = id;
		this.status = status;
		this.evalsheetTitle = evalsheetTitle;
		this.appreciation = appreciation;
	}
	public String getEvalsheetTitle() {
		return evalsheetTitle;
	}
	public void setEvalsheetTitle(String evalsheetTitle) {
		this.evalsheetTitle = evalsheetTitle;
	}
	public boolean isStatus() {
		return status;
	}
	public EvaluationSheet(int id, boolean status, String appreciation) {
		super();
		this.id = id;
		this.status = status;
		this.appreciation = appreciation;
	}
	public EvaluationSheet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setStatus(boolean status) {
		this.status = status;
	}


	private String appreciation;
	@OneToMany(mappedBy = "evaluationSheet" , cascade = {CascadeType.PERSIST ,CascadeType.REMOVE})
	private List<GoalByEmploye> goalByEmployes = new ArrayList<GoalByEmploye>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppreciation() {
		return appreciation;
	}
	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}
	public List<GoalByEmploye> getGoalByEmployes() {
		return goalByEmployes;
	}
	public void setGoalByEmployes(List<GoalByEmploye> goalByEmployes) {
		this.goalByEmployes = goalByEmployes;
	}
	

	public void addGoalEmploye(GoalByEmploye g) {
			g.setEvaluationSheet(this);
			this.getGoalByEmployes().add(g);
		
	}
	
}