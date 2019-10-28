package Service.skill;

import java.util.List;

import javax.ejb.Remote;

import entity.skill.QuestionResponse;
import entity.skill.QuizQuestion;
@Remote
public interface QuestionServiceRemote {

	public void addQuestion(QuizQuestion question);
	public void addResopnse(QuizQuestion question,QuestionResponse response);
	public List<QuestionResponse> listResponses(QuizQuestion question);
	
}
