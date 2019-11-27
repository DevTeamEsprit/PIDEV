package managedBean.skill;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import Service.skill.CategoryService;
import entity.skill.Category;

@ManagedBean(name = "categoryBean", eager = true)
@SessionScoped
public class CategoryBean {

	@EJB
	CategoryService categoryService;

	private String name;
	private String description;
	boolean canAddCategory = false;
	List<Category> categories;
	long toUpdateCategoryId;

	@PostConstruct
	public void init() {
		System.out.println("init called.");
		categories = getAllCategories();

	}

	public String addCategory() {
		System.out.println("addCategory called.");

		if (name == null || name.isEmpty())
			return "";

		categoryService.addCategory(new Category(name, description));

		name = null;
		description = null;
		
		return "category_crud.xhtml?faces-redirect=true";
	}

	public void verifyExistance(AjaxBehaviorEvent abe) {
		System.out.println("Category: " + name + ".");

		if (name == null || name.isEmpty()) {
			canAddCategory = false;
			return;
		}

		Category selectedCategory = categoryService.getCategoryByName(name);

		canAddCategory = selectedCategory == null;
	}

	public List<Category> getAllCategories() {
		return categoryService.ListAllCategories();
	}

	public String goToEditCategory(long categoryId)
	{
		Category category = categoryService.getCategoryById(categoryId);
		
		if(category == null)
			return "";
		
		toUpdateCategoryId = categoryId;
		name = category.getName();
		description = category.getDescription();
		canAddCategory = true;
		
		return "category_update.xhtml?faces-redirect=true";
	}

	public String updateCategory()
	{
		Category category = categoryService.getCategoryById(toUpdateCategoryId);

		if(category == null)
			return "";
		
		category.setName(name);
		category.setDescription(description);
		
		categoryService.updateCategoryById(category);

		return "category_crud.xhtml?faces-redirect=true";
	}
	
	public String deleteCategory(long categoryId)
	{
		Category category = categoryService.getCategoryById(categoryId);

		if(category == null)
			return "";
		
		categoryService.deleteCategory(category);
		
		return "category_crud.xhtml?faces-redirect=true";
	}
	
	/*
	 * Getters and Setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCanAddCategory() {
		return canAddCategory;
	}

	public void setCanAddCategory(boolean canAddCategory) {
		this.canAddCategory = canAddCategory;
	}

}
