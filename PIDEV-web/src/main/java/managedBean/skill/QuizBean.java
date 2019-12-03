package managedBean.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	String startQuizMsg = "";
	float correctAnswersPercentage = 0f;

	UserQuiz userQuiz;
	QuizQuestion currentQuizQuestion;
	List<UserQuizResponse> userQuestionResponses;
	Map<QuizQuestion, List<UserQuizResponse>> quizQToUserResponseMap;

	
	// Google Calendar Related
	
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

		Category selectedCategory = categoryService.getCategoryById(selectedCategoryId);

		if (selectedCategory == null)
			return;

		skills = skillService.getSkillsByCategoryId(selectedCategoryId);
		skills.stream().forEach(e -> System.out.println(e.getName()));

		if (skills == null || skills.size() == 0) {
			canStartQuiz = false;
			
			return;
		}
		selectedSkillId = skills.get(0).getId();

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		UserSkill userSkill = skillService.getOrCreateUserSkill(user.getId(), selectedSkillId);
		startQuizMsg = "Your current level is: " + userSkill.getLevel();
																			
		refreshQuiz(abe);
	}

	public void refreshQuiz(AjaxBehaviorEvent abe) {
		Quiz quiz = getSelectionQuiz();
		canStartQuiz = quiz != null;

		System.out.println("refreshQuiz is called!");
		System.out.println("canStartQuiz: " + canStartQuiz);

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		UserSkill userSkill = skillService.getOrCreateUserSkill(user.getId(), selectedSkillId);
		startQuizMsg = "Your current level is: " + userSkill.getLevel();

		if (quiz == null) {
			// Disable the quiz starting button
			return;
		}
		
		startQuizMsg = "";
		// Enable quiz button
		// Show current level
	}

	public String goToQuizSelection()
	{
		System.out.println("goToQuizSelection called!");
		return "/skill/quiz_selection?faces-redirect=true";
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

	public boolean getHasPreviousQuestion() {
		if (userQuiz == null)
			return false;

		return userQuiz.getCurrentQuestionIndex() > 0;
	}

	public boolean getHasNextQuestion() {
		if (userQuiz == null)
			return false;

		List<QuizQuestion> quizQuestions = quizService.listQuestions(userQuiz.getQuiz());

		if (quizQuestions == null)
			return false;

		return userQuiz.getCurrentQuestionIndex() < (quizQuestions.size() - 1);
	}

	public String nextQuestion() {
		System.out.println("nextQuestion called!");

		List<QuizQuestion> quizQuestions = quizService.listQuestions(userQuiz.getQuiz());

		if (quizQuestions == null)
			return null;

		int targetQuestionIndex = Math.max(0,
				Math.min(quizQuestions.size() - 1, userQuiz.getCurrentQuestionIndex() + 1));
		System.out.println(targetQuestionIndex);

		boolean finished = targetQuestionIndex == userQuiz.getCurrentQuestionIndex();

		if (finished) {
			System.out.println("finished");
			return "path/to/result/page";
		}

		userQuiz.setCurrentQuestionIndex(targetQuestionIndex);
		quizService.updateUserQuiz(userQuiz);

		// QuizQuestion quizQuestion =
		// quizQuestions.get(userQuiz.getCurrentQuestionIndex());

		return "";
	}

	public String showQuizResult() {
		System.out.println("SHOWING QUIZ RESULT!");

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		quizQToUserResponseMap = quizService.getUserQuizQuestionResponseMap(user.getId(), userQuiz.getQuiz().getId());

		if (quizQToUserResponseMap == null)
			return null;

		float correctQuestionsCount = 0;
		float questionsCount = quizQToUserResponseMap.keySet().size();

		for (QuizQuestion key : quizQToUserResponseMap.keySet()) {

			boolean questionAnsweredCorrectly = true;

			for (UserQuizResponse uqr : quizQToUserResponseMap.get(key)) {
				// We at least found a wrong answer
				if (uqr.getIsChecked() != uqr.getResponse().getIsCorrect()) {
					questionAnsweredCorrectly = false;
					break;
				}
			}

			if (questionAnsweredCorrectly)
				correctQuestionsCount++;
		}

		correctAnswersPercentage = (int) (correctQuestionsCount / questionsCount) * 100;
		
		userQuiz.setScore((int)correctAnswersPercentage);

		// Check if this percentage is enough to pass the quiz.
		if (correctAnswersPercentage >= userQuiz.getQuiz().getMinCorrectQuestionsPercentage()) {
			// Now, depending on the user type, we update skill level...
			
			// Meaning an employee
			if (userQuiz.getUser().isActif() == true) {
				
				UserSkill userSkill = skillService.getOrCreateUserSkill(user.getId(), selectedSkillId);
				userSkill.setLevel(Math.max(userSkill.getLevel() + 1, userQuiz.getQuiz().getRequiredMinLevel()));
				skillService.updateUserSkill(userSkill);
				
			} else // Else, a candidate
			{
				// Use calendar to schedule an meet-up
				
			}

		}

		String navTo = "/skill/quiz_result?faces-redirect=true";
		return navTo;
	}

	public String previousQuestion() {
		System.out.println("previousQuestion called!");

		List<QuizQuestion> quizQuestions = quizService.listQuestions(userQuiz.getQuiz());

		if (quizQuestions == null)
			return null;

		if (userQuiz.getCurrentQuestionIndex() <= 0) {
			userQuiz.setCurrentQuestionIndex(0);
			quizService.updateUserQuiz(userQuiz);
			return "";
		}

		int targetQuestionIndex = userQuiz.getCurrentQuestionIndex() - 1;
		userQuiz.setCurrentQuestionIndex(targetQuestionIndex);
		quizService.updateUserQuiz(userQuiz);

		return "";
	}

	public QuizQuestion getCurrentQuizQuestion() {
		if (userQuiz == null)
			return null;

		List<QuizQuestion> quizQuestions = quizService.listQuestions(userQuiz.getQuiz());

		if (quizQuestions == null)
			return null;

		QuizQuestion quizQuestion = quizQuestions.get(userQuiz.getCurrentQuestionIndex());

		return quizQuestion;
	}

	public void updateUserQuestionResponse(long responseId, boolean toChecked) {

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		System.out.println("updateUserQuestionResponse called!!!");

		UserQuizResponse userQuizResponse = questionService.getOrCreateUserQuestionResponse(user.getId(), responseId);

		if (userQuizResponse == null) {
			System.out.println("Got non-valid user quiz response with responseId: " + responseId);
			return;
		}

		System.out.println("INPUT: " + responseId + "|" + toChecked);

		userQuizResponse.setIsChecked(toChecked);
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

		startQuizMsg = "Your current level is: " + userSkill.getLevel();
		
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

	public String getStartQuizMsg()
	{
		return startQuizMsg;
	}
	
	public void setStartQuizMsg(String startQuizMsg)
	{
		this.startQuizMsg = startQuizMsg;
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

	public List<UserQuizResponse> getUserQuestionResponses() {

		userQuestionResponses = new ArrayList<UserQuizResponse>();

		Utilisateur user = new Utilisateur();
		user.setId(1);// lb.getUser();

		List<QuestionResponse> questionResponses = questionService.listResponses(getCurrentQuizQuestion());

		// Init or check for user-response rows in database
		for (QuestionResponse questionResponse : questionResponses) {
			UserQuizResponse uqr = questionService.getOrCreateUserQuestionResponse(user.getId(),
					questionResponse.getId());
			userQuestionResponses.add(uqr);
		}

		return userQuestionResponses;
	}

	public void setUserQuestionResponses(List<UserQuizResponse> userQuestionResponses) {
		this.userQuestionResponses = userQuestionResponses;
	}

	public UserQuiz getUserQuiz() {
		return userQuiz;
	}

	public void setUserQuiz(UserQuiz userQuiz) {
		this.userQuiz = userQuiz;
	}

	public Map<QuizQuestion, List<UserQuizResponse>> getQuizQToUserResponseMap() {
		return quizQToUserResponseMap;
	}

	public void setQuizQToUserResponseMap(Map<QuizQuestion, List<UserQuizResponse>> quizQToUserResponseMap) {
		this.quizQToUserResponseMap = quizQToUserResponseMap;
	}

	public float getCorrectAnswersPercentage() {
		return correctAnswersPercentage;
	}

	public void setCorrectAnswersPercentage(float correctAnswersPercentage) {
		this.correctAnswersPercentage = correctAnswersPercentage;
	}
}
