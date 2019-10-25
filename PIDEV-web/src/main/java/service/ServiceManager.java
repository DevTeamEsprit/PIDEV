package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import Service.CommentaireService;
import Service.PublicationService;
import Service.UtilisateurService;
import entity.Commentaire;
import entity.Contrat;
import entity.Employe;
import entity.Publication;
import entity.Utilisateur;

public class ServiceManager implements Serializable{
	@EJB
	private PublicationService publicationService;
	@EJB
	private UtilisateurService utilisateurService;
	
	@EJB
	private CommentaireService commentaireService;
	
	public void addUser(Employe user )  {
		this.utilisateurService.addUser(user);
	}
	
	public List<Publication> getPubs(){
		return publicationService.listerPub();
	}
	
	public void addPub(Publication publication){
		publicationService.addPublicaion(publication);
	}
	
	public void addCom(Commentaire c) {
		commentaireService.addComm(c);
	}
	
	public void deleteCom(long id_com) {
		commentaireService.delete(id_com);
	}
	
	public Utilisateur getUser() {
		return utilisateurService.getUser(1L);
	}
	 
}
