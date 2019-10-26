package entity.skill;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import entity.Utilisateur;

@Entity
public class UserQuizResponse implements Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
	private Utilisateur user;
	@ManyToOne
	@JoinColumn(name="quiz_id", referencedColumnName="id", insertable = false, updatable = false)
	private Quiz quiz;
	@ManyToOne
	@JoinColumn(name="response_id", referencedColumnName="id", insertable = false, updatable = false)
	private QuestionResponse response;
	private boolean isChecked;
	
	public UserQuizResponse(int id, Utilisateur user, Quiz quiz, QuestionResponse response, boolean isChecked) {
		super();
		this.id = id;
		this.user = user;
		this.quiz = quiz;
		this.response = response;
		this.isChecked = isChecked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public QuestionResponse getResponse() {
		return response;
	}

	public void setResponse(QuestionResponse response) {
		this.response = response;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	
}
