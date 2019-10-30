
package service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import Service.CommentaireService;
import Service.MessageService;
import Service.PublicationService;
import Service.UtilisateurService;
import dto.PublicationCommentaireDto;
import entity.Commentaire;
import entity.Contrat;
import entity.Employe;
import entity.Message;
import entity.Publication;
import entity.Utilisateur;

public class ServiceManager implements Serializable {
	@EJB
	private PublicationService publicationService;
	@EJB
	private UtilisateurService utilisateurService;

	@EJB
	private CommentaireService commentaireService;

	@EJB
	private MessageService messageService;

	public void goToPage(String page) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("../page/" + page);
	}

	public void addUser(Employe user) {
		this.utilisateurService.addUser(user);
	}

	public List<Employe> listerEmploye() {
		return this.utilisateurService.consulterEmploye();
	}

	public List<Publication> getPubs() {
		return this.publicationService.listerPub();
	}

	public List<Publication> getPubsUser(Employe emp) {
		return this.publicationService.getuserPub(emp);
	}

	public void addPub(Publication publication) {
		this.publicationService.addPublicaion(publication);
	}

	public void addCom(Commentaire c) {
		this.commentaireService.addComm(c);
	}

	
	public void deleteCom(long id_com) {
		this.commentaireService.delete(id_com);
	}
	

	public Utilisateur getUser(Long iduser) {
		return this.utilisateurService.getUser(iduser);
	}

	public Utilisateur doLogin(String login, String password) {
		Utilisateur user = this.utilisateurService.doLogin(login, password);
		return user;

	}

	public void addMessage(Message m) {
		this.messageService.add(m);
	}

	public List<Message> getMessages(Utilisateur sender, Utilisateur receiver) {
		return this.messageService.getMessages(sender, receiver);
	}

	public List<Utilisateur> getUsers() {
		List<Utilisateur> users;
		users = this.utilisateurService.getUsers();
		if (users == null) {
			users = new ArrayList<>();
		}

		return users;
	}


	public void deletePub(long idPub) {
		this.publicationService.deletePublication(idPub);
		
	}
	
	public static String MD5(String password) 
	{
		  try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(password.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return password;
	}
	public void updatpassword(Utilisateur user) {
		this.utilisateurService.updatepass((Employe)user);
	}
}
  