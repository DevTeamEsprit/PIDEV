package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GoalByEmployeId implements Serializable{
	private long employeId;
	private int GoalId;
	
	public GoalByEmployeId(long employeId, int goalId) {
		super();
		this.employeId = employeId;
		GoalId = goalId;
	}
	
	public long getEmployeId() {
		return employeId;
	}
	public void setEmployeId(long employeId) {
		this.employeId = employeId;
	}
	public int getGoalId() {
		return GoalId;
	}
	public void setGoalId(int goalId) {
		GoalId = goalId;
	}

	public GoalByEmployeId() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
