package Service.skill;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Utilisateur;
import entity.skill.*;

@Stateless
@LocalBean
public class SkillService implements SkillServiceRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public int addSkill(Skill skill) {
		em.persist(skill);
		return skill.getId();

	}

	@Override
	public Skill updateSkill(Skill skill) {
		em.persist(skill);
		return skill;
	}

	@Override
	public void deleteSkill(Skill skill) {
		em.remove(skill);

	}

	@Override
	public Skill addCategory(Skill skill, Category category) {
		skill.setCategory(category);
		em.persist(skill);
		return skill;
	}

	@Override
	public List<Utilisateur> listUsers(Skill skill) {
		TypedQuery<Utilisateur> query = em.createQuery("Select u from Utilisateur where u.skill=:skill",
				Utilisateur.class);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	}

	@Override
	public List<Quiz> listQuizzes(Skill skill) {
		TypedQuery<Quiz> query = em.createQuery("Select q from Quiz where q.skill=:skill", Quiz.class);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	}

	@Override
	public List<Skill> getSkillsByCategoryId(int categoryId) {
		List<Skill> skills = em.createQuery("SELECT S FROM " + Skill.class.getName() + " S"
				+ " WHERE S.category.id = :categoryId", Skill.class)
				.setParameter("categoryId", categoryId)
				.getResultList();
		
		return skills;
	}

	@Override
	public UserSkill getOrCreateUserSkill(int userId, int skillId)
	{		
		List<UserSkill> userSkills = em
				.createQuery("SELECT US FROM " + UserSkill.class.getName() + " US"
						+ " WHERE US.user.id = :userId AND US.skill.id = :skillId", UserSkill.class)
				.setParameter("userId", userId)
				.setParameter("skillId", skillId)
				.getResultList();

		UserSkill userSkill = null;

		// Does it exist?
		if (userSkills == null || userSkills.size() == 0) {
			// Then create one

			Utilisateur user = em.find(Utilisateur.class, userId);

			if (user == null) {
				System.out.println("Got a non-valid user id: " + userId + ".");
				return null;
			}

			Skill skill = em.find(Skill.class, skillId);

			if (skill == null) {
				System.out.println("Got a non-valid skill id: " + skillId + ".");
				return null;
			}

			// 0 for actual relation level, to start with quiz 1, and so...
			userSkill = new UserSkill(user, skill, 0);

		} else {
			userSkill = userSkills.get(0);
		}

		return userSkill;
		
	}
}
