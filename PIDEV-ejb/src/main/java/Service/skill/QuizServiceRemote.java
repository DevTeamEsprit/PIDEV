package Service.skill;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entity.skill.Quiz;
import entity.skill.QuizQuestion;
import entity.skill.Skill;
import entity.skill.UserQuiz;
import entity.skill.UserQuizResponse;

@Remote
public interface QuizServiceRemote {

	public void ajouterQuiz(Quiz quiz);
	public void addSkillQuiz(Quiz quiz,Skill skill);
	public void addQuestions(Quiz quiz,QuizQuestion question);
	public List<QuizQuestion> listQuestions(Quiz quiz);
	
	public UserQuiz getOrCreateUserQuiz(long userId, long quizId);
	public Quiz getQuizOfSkillWithLevel(long skillId, int quizLevel);
	void updateUserQuiz(UserQuiz userQuiz);
	public List<QuizQuestion> listQuestionsByQuizId(long quizId);
	public void updateUserQuizQuestionIndex(long userQuizId, int newIndex);
	
	public Map<QuizQuestion, List<UserQuizResponse>> getUserQuizQuestionResponseMap(long userId, long quizId);
	
}
