package managedBean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import entity.Utilisateur;
import service.ServiceManager;
@ManagedBean
@ViewScoped
public class ChatBean implements Serializable {

	private Utilisateur userChat = new Utilisateur();

	@Inject
	private ServiceManager serviceManager;

	@PostConstruct
	public void init() {
		Map<String, String> paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (paramMap.get("id") != null) {
			this.setUserChat(this.serviceManager.getUser(Long.parseLong(paramMap.get("id"))));
		}
	}

	public Utilisateur getUserChat() {
		return userChat;
	}

	public void setUserChat(Utilisateur userChat) {
		this.userChat = userChat;
	}
}
