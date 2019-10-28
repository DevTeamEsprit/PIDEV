package managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import entity.Employe;
import entity.Utilisateur;
import service.ServiceManager;

@ManagedBean
@ViewScoped
public class Profilebean implements Serializable {
	
	private Employe selectedEmploye;
	
	@Inject
	private ServiceManager serviceManager;
	
	@ManagedProperty(value = "#{loginbean}")
	private Loginbean lb;
	
	public Loginbean getLb() {
		return lb;
	}

	public void setLb(Loginbean lb) {
		this.lb = lb;
	}
	@PostConstruct
	public void init() throws IOException {
		if (this.lb.getUser() == null) {
			this.serviceManager.goToPage("../login.jsf");
		}
		if(selectedEmploye==null)
			this.selectedEmploye=(Employe) lb.getUser();
		
		System.out.println(selectedEmploye);
		System.out.println(lb.getUser());
		System.out.println("******************");
	}
	
	 public Employe getSelectedEmploye() {
		return selectedEmploye;
	}


	public void setSelectedEmploye(Employe selectedEmploye) {
		this.selectedEmploye = selectedEmploye;
	}


	public String goProfile(Employe u) {
		 this.selectedEmploye=u;
		
		 return "Profile?faces-redirect=true";
	 }
}
