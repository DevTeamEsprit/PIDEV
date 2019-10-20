package entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class EvaluationSheetId implements Serializable {
   private int EmployeId;
   private int EvaluationId;
   private String appreciation;
	private String comment;
	public int getEmployeId() {
		return EmployeId;
	}
	public void setEmployeId(int employeId) {
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
