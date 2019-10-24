package Service.skill;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.skill.*;

@Stateless
@LocalBean
public class CategoryService implements CategoryServiceRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public int addCategory(Category category) {

		em.persist(category);
		return category.getId();
	}

	@Override
	public Category updateCategoryById(Category category) {
		em.persist(category);
		return category;
	}

	@Override
	public void deleteCategory(Category category) {
		em.remove(category);

	}

	@Override
	public List<Skill> listSkills(Category category) {

		TypedQuery<Skill> query = em.createQuery("Select s from Skill where s.category=:category", Skill.class);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	}

}
