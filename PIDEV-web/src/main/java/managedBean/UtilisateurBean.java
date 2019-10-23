package managedBean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;

import Service.UtilisateurService;
import entity.Employe;
import entity.Utilisateur;

@ManagedBean(name="utilisateurbean")
@SessionScoped
public class UtilisateurBean implements Serializable{


	
	private Utilisateur utilisateur= new Utilisateur();


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
}
