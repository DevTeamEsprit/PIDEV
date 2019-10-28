package entity.skill;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import entity.Utilisateur;

@Entity
public class UserSkill implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
	private Utilisateur user;
	@ManyToOne
	@JoinColumn(name="skill_id", referencedColumnName="id", insertable = false, updatable = false)
	private Skill skill;
	private int level;
	
	public UserSkill()
	{
		
	}
	
	public UserSkill(int id, Utilisateur user, Skill skill, int level) {
		super();
		this.id = id;
		this.user = user;
		this.skill = skill;
		this.level = level;
	}
	public UserSkill(Utilisateur user, Skill skill, int level) {
		super();
		this.id = id;
		this.user = user;
		this.skill = skill;
		this.level = level;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
