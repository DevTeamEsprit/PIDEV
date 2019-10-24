package Service.skill;

import javax.ejb.Remote;

import entity.skill.*;

@Remote
public interface SkillServiceRemote {

	public int addSkill(Skill skill);
	public Skill updateSkill(Skill skill);
	public void deleteSkill(Skill skill);
	public Skill addCategory(Skill skill , Category category);
}
