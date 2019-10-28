package entity.skill;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Quiz implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int maxQuizLevel = 5;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Skill skill;

	@OneToMany(mappedBy = "quiz")
	private List<QuizQuestion> questions;

	@Column(name = "required_min_level", columnDefinition = "int default 1")
	private int requiredMinLevel;

	@OneToMany(mappedBy = "quiz")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<QuizQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuizQuestion> questions) {
		this.questions = questions;
	}

	public int getRequiredMinLevel() {
		return requiredMinLevel;
	}

	public void setRequiredMinLevel(int requiredMinLevel) {
		this.requiredMinLevel = requiredMinLevel;
	}

	public static int getMaxquizlevel() {
		return maxQuizLevel;
	}

}
