package entity.skill;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QuestionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean isCorrect;
	private String content;

	@ManyToOne
	private QuizQuestion question;

	public QuestionResponse() {
	}

	public QuestionResponse(int id, boolean isCorrect, String content, QuizQuestion question) {
		super();
		this.id = id;
		this.isCorrect = isCorrect;
		this.content = content;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public QuizQuestion getQuestion() {
		return question;
	}

	public void setQuestion(QuizQuestion question) {
		this.question = question;
	}

}
