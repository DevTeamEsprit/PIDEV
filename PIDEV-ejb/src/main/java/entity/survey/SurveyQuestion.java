package entity.survey;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class SurveyQuestion implements Serializable{

	public SurveyQuestion() {
		// TODO Auto-generated constructor stub
	}
	
	

	public SurveyQuestion(int id, String question) {
		super();
		this.id = id;
		this.question = question;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String question;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
	
}
