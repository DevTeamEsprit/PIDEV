package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Evaluation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID_Eval;
	private EvalType type;
	private Date date;
	
	@ManyToOne
	private Manager manager;
	
	@OneToMany(mappedBy="evaluation")
	private List<Goal> goals = new ArrayList<Goal>();
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public int getID_Eval() {
		return ID_Eval;
	}
	public void setID_Eval(int iD_Eval) {
		ID_Eval = iD_Eval;
	}
	public EvalType getType() {
		return type;
	}
	public void setType(EvalType type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
	

}
