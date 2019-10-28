package Service.skill;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Utilisateur;
import entity.skill.QuestionResponse;
import entity.skill.Quiz;
import entity.skill.QuizQuestion;

@Stateless
@LocalBean
public class QuestionService  implements QuestionServiceRemote{
	@PersistenceContext
    EntityManager em;

	@Override
	public void addQuestion(QuizQuestion question) {
		em.persist(question);
	}

	@Override
	public void addResopnse(QuizQuestion question, QuestionResponse response) {
		question.getResponses().add(response);
		em.persist(question);
	}

	@Override
	public List<QuestionResponse> listResponses(QuizQuestion question) {
		TypedQuery<QuestionResponse> query = em.createQuery("Select r from QuestionResponse where r.question=:question", QuestionResponse.class);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	} 

}
