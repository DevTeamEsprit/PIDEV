package managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import entity.Employe;
import entity.Utilisateur;
import service.ServiceManager;

@ManagedBean
@SessionScoped
public class Loginbean implements Serializable {

	private String login;
	private String password;

	private Utilisateur user;

	private String navigateto;

	@Inject
	ServiceManager serviceManager;

	@PostConstruct
	public void init() {
		if (this.user != null) {
			try {
				this.serviceManager.goToPage("Accueil.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

 
	public String getLogin() {
		return login;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
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

	private boolean type;
	public boolean isType() {
		return type;
	}


	public void setType(boolean type) {
		this.type = type;
	}


	public String Connecter() {

		user = this.serviceManager.doLogin(login, this.serviceManager.MD5(password));
		if (user != null) {
			if(!user.isFirstLogin())
				navigateto = "/page/FirstConnection?faces-redirect=true";
			else
				navigateto = "/page/Accueil?faces-redirect=true";
			
			if(user instanceof Employe)
						 type=true;
			else
				type=false;
		}
		return navigateto;

	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "../Login?face-redirect=true";

	}
	

}
 
