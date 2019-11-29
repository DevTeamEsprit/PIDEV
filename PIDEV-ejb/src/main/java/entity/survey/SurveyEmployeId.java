package entity.survey;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SurveyEmployeId implements Serializable{

	public SurveyEmployeId() {
		// TODO Auto-generated constructor stub
	}

	private long EmpId;
	private int QuestId;
	public SurveyEmployeId(long empId, int questId) {
		super();
		EmpId = empId;
		QuestId = questId;
	}
	public long getEmpId() {
		return EmpId;
	}
	public void setEmpId(long empId) {
		EmpId = empId;
	}
	public int getQuestId() {
		return QuestId;
	}
	public void setQuestId(int questId) {
		QuestId = questId;
	}
	
	
}
