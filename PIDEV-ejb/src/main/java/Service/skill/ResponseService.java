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
public class ResponseService  implements ResponseServiceRemote{
	@PersistenceContext
    EntityManager em;

	@Override
	public void addResponse(QuestionResponse response) {
			em.persist(response);
	}


}
