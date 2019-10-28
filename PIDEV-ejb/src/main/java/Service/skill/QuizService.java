package Service.skill;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Utilisateur;
import entity.skill.Quiz;
import entity.skill.QuizQuestion;
import entity.skill.Skill;
import entity.skill.UserQuiz;

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

	@Override
	public void addQuestions(Quiz quiz, QuizQuestion question) {
		quiz.getQuestions().add(question);
		em.persist(quiz);

	}

	@Override
	public List<QuizQuestion> listQuestions(Quiz quiz) {

		TypedQuery<QuizQuestion> query = em.createQuery("Select q from QuizQuestion where q.quiz=:quiz",
				QuizQuestion.class);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	}

	@Override
	public UserQuiz getOrCreateUserQuiz(int userId, int quizId) {
		List<UserQuiz> userQuizs = em
				.createQuery("SELECT UQ FROM " + UserQuiz.class.getName() + " UQ"
						+ " WHERE UQ.user.id = :userId AND UQ.quiz.id = :quizId", UserQuiz.class)
				.setParameter("userId", userId).setParameter("quizId", quizId).getResultList();

		UserQuiz userQuiz = null;

		// Does it exist?
		if (userQuizs == null || userQuizs.size() == 0) {
			// Then create one

			Utilisateur user = em.find(Utilisateur.class, userId);

			if (user == null) {
				System.out.println("Got a non-valid user id: " + userId + ".");
				return null;
			}

			Quiz quiz = em.find(Quiz.class, quizId);

			if (quiz == null) {
				System.out.println("Got a non-valid quiz id: " + quizId + ".");
				return null;
			}

			userQuiz = new UserQuiz(user, quiz, 1);

		} else {
			userQuiz = userQuizs.get(0);
		}

		return userQuiz;
	}

}
