package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Commentaire;
import entity.Publication;
import service.ServiceManager;

@Named
@ViewScoped
public class CommentaireBean implements Serializable {
	
	//public List<String> strings  = new ArrayList<String>(); 
	
	private Commentaire commentaire = new Commentaire();
	private String description; 
	
	@Inject
	private ServiceManager serviceManager;

	public Commentaire getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Commentaire commentaire) {
		this.commentaire = commentaire;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public void addComm(Publication pub) {
		commentaire.setDateCreation(new Date());
		commentaire.setPub(pub);
	//	System.out.println(commentaire);
		 
	 
	}
	
	
}
