package Service.skill;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.skill.Quiz;
import entity.skill.Skill;

@Stateless
@LocalBean
public class QuizService implements QuizServiceRemote {


	@PersistenceContext
	EntityManager em;
	@Override
	public void ajouterQuiz(Quiz quiz) {
		em.persist(quiz);
		
	}

	@Override
	public void addSkillQuiz(Quiz quiz, Skill skill) {
		quiz.setSkill(skill);
		em.persist(quiz);
		
	}

}
