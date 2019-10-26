package managedBean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.UtilisateurService;
import entity.Utilisateur;

@ManagedBean
@SessionScoped
public class Loginbean implements Serializable{
	
	private String login;
	private String password;
	private Utilisateur e = new Utilisateur();

	@EJB
	UtilisateurService utilisateurService;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
