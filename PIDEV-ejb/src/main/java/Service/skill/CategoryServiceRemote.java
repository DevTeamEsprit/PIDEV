package Service.skill;

import java.util.List;

import javax.ejb.Remote;

import entity.Category;
import entity.Skill;

@Remote
public interface CategoryServiceRemote {

	public int addCategory(Category category);
	public Category updateCategoryById(Category category);
	public void deleteCategory(Category category);
	public List<Skill> listSkills(Category category);
}
