package Service.skill;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		// TODO Auto-generated method stub
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

}
