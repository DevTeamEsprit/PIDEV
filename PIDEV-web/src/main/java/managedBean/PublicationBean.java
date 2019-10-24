package managedBean;

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
import entity.Commentaire;
import entity.Employe;
import entity.Manager;
import entity.Publication;
import entity.Utilisateur;
import service.ServiceManager;

@Named
@ViewScoped
public class PublicationBean implements Serializable{

	private Publication publication = new Publication();
	 
	private Utilisateur user;
		
	private List<Publication> lstPublications;
	Map<Publication, Commentaire> mapPub = new HashMap<>();
	
	private Commentaire commentaire = new Commentaire();
	
	@Inject
	private ServiceManager serviceManager;
	

	@PostConstruct
	public void init() {	
		user = serviceManager.getUser();
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
		publication.setDateCreation(new Date());
		serviceManager.addPub(publication);
		publication  = new Publication();
		this.getPubs();
	}
	
	
	public Map<Publication, Commentaire> getMapPub() {
		return mapPub;
	}



	public void setMapPub(Map<Publication, Commentaire> mapPub) {
		this.mapPub = mapPub;
	}



	private void getPubs() {
		this.lstPublications = serviceManager.getPubs();
		if(this.lstPublications == null) {
			this.lstPublications =  new ArrayList<>();
		}
		this.mapPub=this.convertListBeforeJava8(lstPublications);
		
	}
	public void addComm(Commentaire com) {
		commentaire.setDateCreation(new Date());
		this.serviceManager.addCom(com);
		this.getPubs();
 
 
	}
	
	
	public Map<Publication, Commentaire> convertListBeforeJava8(List<Publication> list) {
		Map<Publication, Commentaire> map = new HashMap<>();
	    for (Publication pub : list) {
	        map.put(pub,new Commentaire(pub));
	    }
	    return map;
	}
	 
}
