package managedBean.skill;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import Service.skill.CategoryService;
import Service.skill.QuizService;
import Service.skill.SkillService;
import entity.Utilisateur;
import entity.skill.Category;
import entity.skill.Quiz;
import entity.skill.Skill;
import entity.skill.UserQuiz;
import entity.skill.UserSkill;
import managedBean.Loginbean;

@ManagedBean(name = "quizBean", eager = true)
@SessionScoped
public class QuizBean {

	@EJB
	CategoryService categoryService;
	@EJB
	SkillService skillService;
	@EJB
	QuizService quizService;

	int selectedCategoryId;
	int selectedSkillId;
	int selectedQuizId;
	
	Category selectedCategory;
	Skill selectedSkill;
	UserQuiz userQuiz;

	@ManagedProperty(value = "#{loginBean}")
	private Loginbean lb;
	
	List<Category> categories;
	List<Skill> skills;

	@PostConstruct
	private void init() {
		categories = categoryService.ListAllCategories();

		if (selectedCategoryId > 0)
			skills = skillService.getSkillsByCategoryId(selectedCategoryId);
	}

	/***
	 * Changes skills array relatively to the selected category.
	 * 
	 * @param abe
	 */
	public void changeSkill(AjaxBehaviorEvent abe) {
		System.out.println("Changing skill! Category: " + selectedCategoryId + ".");
		
		selectedCategory = categoryService.getCategoryById(selectedCategoryId);
		
		skills = skillService.getSkillsByCategoryId(selectedCategoryId);
		skills.stream().forEach(e -> System.out.println(e.getName()));
	}

	public String goToQuiz() {
		String navTo = "";

		if (selectedCategoryId == 0 || selectedSkillId == 0)
			return null;
		navTo = "/skill/quiz_attempt?faces-redirect=true";

		// How to get connected user ID?
		Utilisateur user = lb.getUser();
		
		// Before knowing the quiz, what's the level of the user with the selected skill?
		UserSkill userSkill = skillService.getOrCreateUserSkill((int)user.getId(), selectedSkillId);
		int lookForLevel = userSkill.getLevel() + 1;
		
		Quiz quiz = quizService.getQuizOfSkillWithLevel(selectedSkillId, lookForLevel);
		
		if(quiz == null)
			return null; // Revise this...
		
		// What quiz to select?
		int quizId = quiz.getId();
		
		// Check if UserQuiz exists, create one if not, and get it
		userQuiz = quizService.getOrCreateUserQuiz((int)user.getId(), quizId);
		
		return navTo;
	}

	/*
	 * Getters & Setters
	 */

	public int getSelectedCategoryId() {
		return selectedCategoryId;
	}

	public void setSelectedCategoryId(int selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}

	public int getSelectedSkillId() {
		return selectedSkillId;
	}

	public void setSelectedSkillId(int selectedSkillId) {
		this.selectedSkillId = selectedSkillId;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public Skill getSelectedSkill() {
		return selectedSkill;
	}

	public void setSelectedSkill(Skill selectedSkill) {
		this.selectedSkill = selectedSkill;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

}
