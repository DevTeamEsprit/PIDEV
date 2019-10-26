package Service.skill;

import javax.ejb.Remote;

import entity.skill.Quiz;
import entity.skill.Skill;

@Remote
public interface QuizServiceRemote {

	public void ajouterQuiz(Quiz quiz);
	public void addSkillQuiz(Quiz quiz,Skill skill);
}
