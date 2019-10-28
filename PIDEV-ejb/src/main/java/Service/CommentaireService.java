package Service;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Commentaire;
import entity.Employe;
import entity.Manager;
import entity.Publication;
import entity.Utilisateur;

/**
 * Session Bean implementation class CommentaireService
 */
@Stateless
@LocalBean
public class CommentaireService {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
    EntityManager em; 
	
	
    public CommentaireService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addComm(Commentaire c) {
    	em.persist(c);
    }
    
    public void delete(long id_com) {
    	em.remove(em.find(Commentaire.class,id_com));
    }
     

}