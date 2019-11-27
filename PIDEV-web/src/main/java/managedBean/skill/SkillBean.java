package managedBean.skill;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import Service.skill.CategoryService;
import Service.skill.SkillService;
import entity.skill.Category;
import entity.skill.Skill;

@ManagedBean(name = "skillBean", eager = true)
@SessionScoped
public class SkillBean {

	@EJB
	SkillService skillService;
	@EJB
	CategoryService categoryService;

	private String name;
	private String description;
	private long categoryId; // to add

	boolean canAddSkill = false;
	List<Skill> skills;
	List<Category> categories;

	long toUpdateSkillId;

	@PostConstruct
	public void init() {
		System.out.println("init called.");
		skills = getAllSkills();
		categories = categoryService.ListAllCategories();

	}

	public String addSkill() {
		System.out.println("addSkill called.");

		if (name == null || name.isEmpty())
			return "";

		Category category = categoryService.getCategoryById(categoryId);

		if (category == null)
			return "";

		skillService.addSkill(new Skill(name, description, category));

		name = null;
		description = null;

		return "skill_crud.xhtml?faces-redirect=true";
	}

	public void verifyExistance(AjaxBehaviorEvent abe) {
		System.out.println("Skill: " + name + ".");

		if (name == null || name.isEmpty()) {
			canAddSkill = false;
			return;
		}

		Skill selectedSkill = skillService.getSkillByName(name);

		Category category = categoryService.getCategoryById(categoryId);

		if (category == null) {
			canAddSkill = false;
			return;
		}

		canAddSkill = selectedSkill == null;
	}

	public List<Skill> getAllSkills() {
		return categoryService.getAllSkills();
	}

	public String goToEditSkill(long skillId) {
		Skill skill = skillService.getSkillById(skillId);

		if (skill == null)
			return "";

		toUpdateSkillId = skillId;
		name = skill.getName();
		description = skill.getDescription();
		canAddSkill = true;

		return "skill_update.xhtml?faces-redirect=true";
	}

	public String updateSkill() {
		Skill skill = skillService.getSkillById(toUpdateSkillId);

		if (skill == null)
			return "";

		skill.setName(name);
		skill.setDescription(description);

		skillService.updateSkill(skill);

		return "skill_crud.xhtml?faces-redirect=true";
	}

	public String deleteSkill(long skillId) {
		Skill skill = skillService.getSkillById(skillId);

		if (skill == null)
			return "";

		skillService.deleteSkill(skill);

		return "skill_crud.xhtml?faces-redirect=true";
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

	public boolean isCanAddSkill() {
		return canAddSkill;
	}

	public void setCanAddSkill(boolean canAddSkill) {
		this.canAddSkill = canAddSkill;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
