package entity.skill;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import entity.Utilisateur;

@Entity
public class UserQuiz implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Utilisateur user;
	@ManyToOne
	@JoinColumn(name = "quiz_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Quiz quiz;
	private int score;

	@Column(name = "curr_question_index")
	private int currentQuestionIndex;
	
	public UserQuiz()
	{
		
	}

	public UserQuiz(long id, Utilisateur user, Quiz quiz, int score) {
		super();
		this.id = id;
		this.user = user;
		this.quiz = quiz;
		this.score = score;
	}

	public UserQuiz(Utilisateur user, Quiz quiz, int score) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.score = score;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getCurrentQuestionIndex() {
		return currentQuestionIndex;
	}

	public void setCurrentQuestionIndex(int currentQuestionIndex) {
		this.currentQuestionIndex = currentQuestionIndex;
	}
}
