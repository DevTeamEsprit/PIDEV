package dto;

import entity.Commentaire;
import entity.Publication;

public class PublicationCommentaireDto {

	private Publication publication;
	private Commentaire commentaire;

	public PublicationCommentaireDto() {
		// TODO Auto-generated constructor stub
	}

	public PublicationCommentaireDto(Publication publication, Commentaire commentaire) {
		this.setPublication(publication);
		this.setCommentaire(commentaire);
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public Commentaire getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Commentaire commentaire) {
		this.commentaire = commentaire;
	}
}
