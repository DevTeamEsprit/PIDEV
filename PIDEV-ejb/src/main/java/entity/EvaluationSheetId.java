package entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class EvaluationSheetId implements Serializable {
   private long EmployeId;
   private int EvaluationId;
   private String appreciation;
	private String comment;
	private boolean status;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public EvaluationSheetId(long employeId, int evaluationId, String appreciation, String comment, boolean status) {
		super();
		EmployeId = employeId;
		EvaluationId = evaluationId;
		this.appreciation = appreciation;
		this.comment = comment;
		this.status = status;
	}
	public long getEmployeId() {
		return EmployeId;
	}
	public void setEmployeId(long employeId) {
		EmployeId = employeId;
	}
	
	public int getEvaluationId() {
		return EvaluationId;
	}
	public void setEvaluationId(int evaluationId) {
		EvaluationId = evaluationId;
	}
	public String getAppreciation() {
		return appreciation;
	}
	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
