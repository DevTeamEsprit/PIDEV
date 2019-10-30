package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Utilisateur;
import service.ServiceManager;

@Named
@ViewScoped
public class ContactsBean implements Serializable {

		
	@Inject
	private ServiceManager serviceManager;

	private List<Utilisateur> contacts;

	@PostConstruct
	public void init() {
		this.contacts = this.serviceManager.getUsers();
	}

	public List<Utilisateur> getContacts() {
		return contacts;
	}

	public void setContacts(List<Utilisateur> contacts) {
		this.contacts = contacts;
	}

	public void goToChat(Utilisateur user) throws IOException {
		this.serviceManager.goToPage("Chat.jsf?id="+user.getId());
	}
}
