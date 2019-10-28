package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.CommentaireService;
import Service.PublicationService;
import Service.UtilisateurService;
import dto.PublicationCommentaireDto;
import entity.Commentaire;
import entity.Employe;
import entity.Manager;
import entity.Publication;
import entity.Utilisateur;
import service.ServiceManager;

@ManagedBean
@ViewScoped
public class PublicationBean implements Serializable {

	@ManagedProperty(value = "#{loginbean}")
	private Loginbean lb;

	private Publication publication = new Publication();

	private Utilisateur user;

	private List<Publication> lstPublications;

	private List<PublicationCommentaireDto> list = new ArrayList<>();

	
	@Inject
	private ServiceManager serviceManager;

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
		this.getPubs();
	 
		 
		
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public List<Publication> getLstPublications() {
		return lstPublications;
	}

	public void setLstPublications(List<Publication> lstPublications) {
		this.lstPublications = lstPublications;
	}

	public void addPub() {
		publication.setUser(lb.getUser());
		publication.setDateCreation(new Date());
		serviceManager.addPub(publication);
		publication = new Publication();
		this.getPubs();
	}

	private void getPubs() {
		this.lstPublications = serviceManager.getPubs();
		if (this.lstPublications == null) {
			this.lstPublications = new ArrayList<>();
		}
		this.list = this.convertListBeforeJava8(this.lstPublications);

	}
	

	public void addComm(Commentaire com) {
		com.setUser(lb.getUser());
		com.setDateCreation(new Date());
		this.serviceManager.addCom(com);
		this.getPubs();

	}

	public List<PublicationCommentaireDto> convertListBeforeJava8(List<Publication> l) {
		List<PublicationCommentaireDto> list = new ArrayList<>();
		for (Publication pub : l) {
			list.add(new PublicationCommentaireDto(pub, new Commentaire(pub)));
		}
		return list;
	}


	public List<PublicationCommentaireDto> getList() {
		return list;
	}

	public void setList(List<PublicationCommentaireDto> list) {
		this.list = list;
	}
	
	public void deleteComentaire(Commentaire com) {
		this.serviceManager.deleteCom(com.getId());
		this.getPubs();
	}
}