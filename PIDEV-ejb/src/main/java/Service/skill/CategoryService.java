package Service.skill;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.skill.*;

@Stateless
@LocalBean
public class CategoryService implements CategoryServiceRemote {

	@PersistenceContext
	EntityManager em;
	

	@Override
	public Category addCategory(Category category) {

		em.persist(category);
		return category;
	}

	@Override
	public Category updateCategoryById(Category category) {
		em.persist((em.contains(category) ? category : em.merge(category)));
		return category;
	}

	@Override
	public void deleteCategory(Category category) {
		em.remove(category);

	}

	@Override
	public List<Skill> getAllSkills() {

		TypedQuery<Skill> query = em.createQuery("Select s from Skill s", Skill.class);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	}
	
	
	@Override
	public List<Skill> listSkills(Category category) {

		TypedQuery<Skill> query = em.createQuery("Select s from Skill where s.category=:category", Skill.class).setParameter("category", category);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	}

	@Override
	public List<Category> ListAllCategories() {
		TypedQuery<Category> query = em.createQuery("Select c from Category c ", Category.class);
		try {
			return query.getResultList();
		}

		catch (Exception e) {
			System.out.print("error");
		}
		return null;
	}
	
	@Override
	public Category getCategoryById(long categoryId)
	{
		Category category = em.find(Category.class, categoryId);
		
		return category;
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		Query query=em.createQuery("SELECT C FROM " + Category.class.getName()+" C WHERE C.name=:name");
		query.setParameter("name", categoryName);
		List<Category> categories=query.getResultList();
		if(categories==null || categories.size()==0)
			return null;
		return categories.get(0);
	}

}
