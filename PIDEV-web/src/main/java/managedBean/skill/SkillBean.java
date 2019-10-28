package managedBean.skill;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.skill.SkillService;
import entity.skill.Category;
import entity.skill.Skill;

@ManagedBean
@SessionScoped
public class SkillBean {
	private String name;
	private String description;
	private Category category;
	
	@EJB
	SkillService skillService;
	public void addSkill() {
		skillService.addSkill(new Skill(name , category,description));
	}
}
