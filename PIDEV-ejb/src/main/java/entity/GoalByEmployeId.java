package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GoalByEmployeId implements Serializable{
	private long employeId;
	private int GoalId;
	private int noteByEmp;
	private int noteByMan;
	public int getNoteByEmp() {
		return noteByEmp;
	}
	public void setNoteByEmp(int noteByEmp) {
		this.noteByEmp = noteByEmp;
	}
	public GoalByEmployeId(long employeId, int goalId, int noteByEmp, int noteByMan) {
		super();
		this.employeId = employeId;
		GoalId = goalId;
		this.noteByEmp = noteByEmp;
		this.noteByMan = noteByMan;
	}
	public int getNoteByMan() {
		return noteByMan;
	}
	public void setNoteByMan(int noteByMan) {
		this.noteByMan = noteByMan;
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
