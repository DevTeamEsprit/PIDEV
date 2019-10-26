package entity.skill;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class QuizQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String content;
	
	@ManyToMany(mappedBy="questions")
	private List<Quiz> quizzes;
	
	@OneToMany(mappedBy="question")
	private List<QuestionResponse> responses;

	public QuizQuestion() {}
	public QuizQuestion(int id, String content, List<Quiz> quizzes, List<QuestionResponse> responses) {
		super();
		this.id = id;
		this.content = content;
		this.quizzes = quizzes;
		this.responses = responses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public List<QuestionResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<QuestionResponse> responses) {
		this.responses = responses;
	}
	
	
}