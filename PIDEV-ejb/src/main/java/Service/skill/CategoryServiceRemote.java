package Service.skill;

import java.util.List;

import javax.ejb.Remote;

import entity.skill.*;

@Remote
public interface CategoryServiceRemote {

	public Category addCategory(Category category);
	public Category updateCategoryById(Category category);
	public void deleteCategory(Category category);
	public List<Skill> listSkills(Category category);
	public List<Category> ListAllCategories();
	public Category getCategoryById(long categoryId);
}
