package managedBean.skill;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import Service.skill.CategoryService;
import Service.skill.QuestionService;
import Service.skill.QuizService;
import Service.skill.SkillService;
import entity.Utilisateur;
import entity.skill.Category;
import entity.skill.QuestionResponse;
import entity.skill.Quiz;
import entity.skill.QuizQuestion;
import entity.skill.Skill;
import entity.skill.UserQuiz;
import entity.skill.UserQuizResponse;
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
	@EJB
	QuestionService questionService;

	long selectedCategoryId;
	long selectedSkillId;
	long selectedQuizId;

	boolean canStartQuiz = false;

	Category selectedCategory;
	Skill selectedSkill;
	UserQuiz userQuiz;
	QuizQuestion currentQuizQuestion;
	List<QuestionResponse> questionResponses;
	List<Long> selectedResponseIds;

	// @ManagedProperty(value = "#{loginBean}")
	// private Loginbean lb;

	List<Category> categories;
	List<Skill> skills;

	@PostConstruct
	private void init() {
		categories = categoryService.ListAllCategories();

		canStartQuiz = false;

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

		if (skills == null || skills.size() == 0) {
			canStartQuiz = false;
			return;
		}

		selectedSkillId = skills.get(0).getId();

		refreshQuiz(abe);
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

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		// Check if UserQuiz exists, create one if not, and get it
		userQuiz = quizService.getOrCreateUserQuiz(user.getId(), quizId);

		return navTo;
	}

	public String nextQuestion() {
		System.out.println("nextQuestion called!");
		return "";
	}

	public String previousQuestion() {
		System.out.println("previousQuestion called!");
		return "";
	}

	public QuizQuestion getCurrentQuizQuestion() {
		if (userQuiz == null)
			return null;

		List<QuizQuestion> quizQuestions = quizService.listQuestions(userQuiz.getQuiz());

		if (quizQuestions == null)
			return null;

		return quizQuestions.get(userQuiz.getCurrentQuestionIndex());
	}

	public List<QuestionResponse> getQuestionResponses() {
		QuizQuestion quizQuestion = getCurrentQuizQuestion();

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		List<QuestionResponse> questionResponses = questionService.listResponses(quizQuestion);

		// Init or check for user-response rows in database
		for (QuestionResponse questionResponse : questionResponses)
			questionService.getOrCreateUserQuestionResponse(user.getId(), questionResponse.getId());

		return questionResponses;
	}

	public void updateUserQuestionResponse(long responseId, boolean toChecked) {
		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		UserQuizResponse userQuizResponse = questionService.getOrCreateUserQuestionResponse(user.getId(), responseId);

		if (userQuizResponse == null) {
			System.out.println("Got non-valid user quiz response with responseId: " + responseId);
			return;
		}

		userQuizResponse.setChecked(toChecked);
		questionService.updateUserQuizResponse(userQuizResponse);
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

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		// Before knowing the quiz, what's the level of the user with the selected
		// skill?
		UserSkill userSkill = skillService.getOrCreateUserSkill(user.getId(), selectedSkillId);

		if (userSkill == null) {
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

	public long getSelectedCategoryId() {
		return selectedCategoryId;
	}

	public void setSelectedCategoryId(long selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}

	public long getSelectedSkillId() {
		return selectedSkillId;
	}

	public void setSelectedSkillId(long selectedSkillId) {
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

	public List<Long> getSelectedResponseIds() {
		return selectedResponseIds;
	}

	public void setSelectedResponseIds(List<Long> selectedResponseIds) {
		this.selectedResponseIds = selectedResponseIds;
	}

	public UserQuiz getUserQuiz() {
		return userQuiz;
	}

	public void setUserQuiz(UserQuiz userQuiz) {
		this.userQuiz = userQuiz;
	}

}
