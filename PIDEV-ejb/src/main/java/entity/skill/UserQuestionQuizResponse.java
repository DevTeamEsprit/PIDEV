package entity.skill;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import entity.Utilisateur;

@Entity
public class UserQuestionQuizResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Utilisateur user;

	@ManyToOne
	private QuestionResponse questionResponse;

	@Column(name = "is_checked")
	private boolean isChecked;

	public UserQuestionQuizResponse(Utilisateur user, QuestionResponse questionResponse, boolean isChecked) {
		super();
		this.user = user;
		this.questionResponse = questionResponse;
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

	public QuestionResponse getQuestionResponse() {
		return questionResponse;
	}

	public void setQuestionResponse(QuestionResponse questionResponse) {
		this.questionResponse = questionResponse;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
