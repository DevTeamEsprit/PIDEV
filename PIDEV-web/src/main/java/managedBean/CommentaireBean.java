package managedBean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Commentaire;
import entity.Publication;
import service.ServiceManager;

@Named
@ViewScoped
public class CommentaireBean implements Serializable {
	
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
	
	
	public void addComm() {
		System.out.println(commentaire);
		 
	 
	}
	
	
}
