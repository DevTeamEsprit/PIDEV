package managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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
	public void init() throws IOException {
		if (this.user != null) {
			this.serviceManager.goToPage("Accueil.jsf");
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

	public String Connecter() {

		user = this.serviceManager.doLogin(login, password);
		if (user != null) {
			navigateto = "/page/Accueil?faces-redirect=true";
		}
		return navigateto;

	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?face-redirect=true";

	}
}
 
