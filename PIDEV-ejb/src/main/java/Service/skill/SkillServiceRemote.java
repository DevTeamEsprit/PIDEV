package Service.skill;

import java.util.List;

import javax.ejb.Remote;

import entity.Utilisateur;
import entity.skill.*;

@Remote
public interface SkillServiceRemote {

	public int addSkill(Skill skill);
	public Skill updateSkill(Skill skill);
	public void deleteSkill(Skill skill);
	public Skill addCategory(Skill skill , Category category);
	public List<Utilisateur> listUsers(Skill skill);
	public List<Quiz> listQuizzes(Skill skill);
	public List<Skill> getSkillsByCategoryId(int categoryId);
	public UserSkill getOrCreateUserSkill(int userId, int skillId);
}
