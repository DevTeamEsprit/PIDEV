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
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	private Manager manager;
	
	public Evaluation(int iD_Eval, EvalType type, Date date, Manager manager,boolean status) {
		super();
		ID_Eval = iD_Eval;
		this.type = type;
		this.date = date;
		this.manager = manager;
		this.status = status;
	}
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
