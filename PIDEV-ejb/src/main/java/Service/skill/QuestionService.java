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
import entity.skill.Skill;
import entity.skill.UserQuizResponse;
import entity.skill.UserSkill;

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
		TypedQuery<QuestionResponse> query = em.createQuery("SELECT R FROM " + QuestionResponse.class.getName() + " R WHERE R.question = :question", QuestionResponse.class)
				.setParameter("question", question);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	} 
	
	
	@Override
	public QuizQuestion getQuestionById(int questionId)
	{
		return em.find(QuizQuestion.class, questionId);
		
	}
	
	@Override
	public List<QuestionResponse> listResponsesByQuestionId(int questionId) {
		TypedQuery<QuestionResponse> query = em.createQuery("SELECT R FROM " + QuestionResponse.class.getName() + " R WHERE R.question.id = :questionId", QuestionResponse.class)
				.setParameter("questionId", questionId);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	} 
	
	@Override
	public UserQuizResponse getOrCreateUserQuestionResponse(long userId, long responseId)
	{
		List<UserQuizResponse> userQuizResponses = em
				.createQuery("SELECT UQR FROM " + UserQuizResponse.class.getName() + " UQR"
						+ " WHERE UQR.user.id = :userId AND UQR.response.id = :responseId", UserQuizResponse.class)
				.setParameter("userId", userId)
				.setParameter("responseId", responseId)
				.getResultList();

		UserQuizResponse userQuizResponse = null;

		// Does it exist?
		if (userQuizResponses == null || userQuizResponses.size() == 0) {
			// Then create one

			Utilisateur user = em.find(Utilisateur.class, userId);

			if (user == null) {
				System.out.println("Got a non-valid user id: " + userId + ".");
				return null;
			}

			QuestionResponse response = em.find(QuestionResponse.class, responseId);

			if (response == null) {
				System.out.println("Got a non-valid response id: " + responseId + ".");
				return null;
			}

			userQuizResponse = new UserQuizResponse(user, response, false);
			em.persist(userQuizResponse);

		} else {
			userQuizResponse = userQuizResponses.get(0);
		}

		return userQuizResponse;
		
	}

	@Override
	public void updateUserQuizResponse(UserQuizResponse userQuizResponse)
	{
		//em.merge(userQuizResponse);
		em.persist(em.contains(userQuizResponse) ? userQuizResponse : em.merge(userQuizResponse));
		//em.persist(userQuizResponse);
	}
	
}
