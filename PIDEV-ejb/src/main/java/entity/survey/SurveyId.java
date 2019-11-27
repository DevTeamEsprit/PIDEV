package entity.survey;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class SurveyId implements Serializable{

	public SurveyId() {
		// TODO Auto-generated constructor stub
	}

	
	public SurveyId(long manId, long empId) {
		super();
		this.manId = manId;
		this.empId = empId;
	}

	private long manId;
	private long empId;
	
	
	public long getManId() {
		return manId;
	}
	public void setManId(long manId) {
		this.manId = manId;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
}
