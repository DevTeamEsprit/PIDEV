package Service;

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
    	Utilisateur u =em.find(Utilisateur.class,4L);
    	if(u instanceof Employe)
    		c.setUser((Employe)u);
    	else
    		c.setUser((Manager)u);
    	
    //	System.out.println(c+"service ");
    	em.persist(c);
    }
    
    public void delete(long id_com) {
    	em.remove(em.find(Commentaire.class,id_com));
    }
     

}
