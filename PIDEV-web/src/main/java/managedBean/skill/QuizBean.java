package managedBean.skill;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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

	boolean canStartQuiz = false;

	Category selectedCategory;
	Skill selectedSkill;
	UserQuiz userQuiz;

	//@ManagedProperty(value = "#{loginBean}")
	//private Loginbean lb;

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

	public void refreshQuiz(AjaxBehaviorEvent abe) {
		Quiz quiz = getSelectionQuiz();
		canStartQuiz = quiz != null;

		System.out.println("refreshQuiz is called!");
		System.out.println("canStartQuiz: " + canStartQuiz);
		
		if (quiz == null) {
			// Disable the quiz starting button
			return;
		}

		// Enable quiz button
		// Show current level
	}

	public String goToQuiz() {
		String navTo = "/skill/quiz_attempt?faces-redirect=true";

		Quiz quiz = getSelectionQuiz();

		if (quiz == null)
			return null; // Revise this...

		// What quiz to select?
		long quizId = quiz.getId();

		Utilisateur user = new Utilisateur(); user.setId(1);//lb.getUser();
		
		// Check if UserQuiz exists, create one if not, and get it
		userQuiz = quizService.getOrCreateUserQuiz(user.getId(), quizId);

		return navTo;
	}

	/*
	 * Private Methods
	 */

	/***
	 * Returns the quiz using selected category and skill ids.
	 * 
	 * @return selection quiz.
	 */
	private Quiz getSelectionQuiz() {
		if (selectedCategoryId == 0 || selectedSkillId == 0)
			return null;

		Utilisateur user = new Utilisateur(); user.setId(1);//lb.getUser();

		// Before knowing the quiz, what's the level of the user with the selected
		// skill?
		UserSkill userSkill = skillService.getOrCreateUserSkill(user.getId(), selectedSkillId);
		
		if(userSkill == null)
		{
			System.out.println("Check your user's existence for id: " + user.getId());
			return null;
		}
		
		int lookForLevel = userSkill.getLevel() + 1;

		Quiz quiz = quizService.getQuizOfSkillWithLevel(selectedSkillId, lookForLevel);

		return quiz;
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

	public boolean isCanStartQuiz() {
		return canStartQuiz;
	}

	public void setCanStartQuiz(boolean canStartQuiz) {
		this.canStartQuiz = canStartQuiz;
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
