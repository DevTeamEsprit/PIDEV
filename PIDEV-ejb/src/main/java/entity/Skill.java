package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Skill implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private int level;
	@ManyToOne(cascade= {CascadeType.PERSIST})
	private Category category;
	@OneToMany(mappedBy="skill")
	private List<Quiz> quizzes;
	
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public Skill(int id, String name, String description, int level, Category category, List<Quiz> quizzes) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.level = level;
		this.category = category;
		this.quizzes = quizzes;
	}

	private Skill() {}
	
	public Skill(int id, String name, String description, int level, Category category ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.level = level;
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
