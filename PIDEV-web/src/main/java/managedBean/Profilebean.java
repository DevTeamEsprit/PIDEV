package managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import entity.Employe;
import entity.Utilisateur;

@ManagedBean
@ViewScoped
public class Profilebean implements Serializable {
	
	private Employe selectedEmploye;
	
	
	 public Employe getSelectedEmploye() {
		return selectedEmploye;
	}


	public void setSelectedEmploye(Employe selectedEmploye) {
		this.selectedEmploye = selectedEmploye;
	}


	public String goProfile(Employe u) {
		 this.selectedEmploye=u;
		 System.out.println(this.selectedEmploye);
		 return "Profile?faces-redirect=true";
	 }
}
