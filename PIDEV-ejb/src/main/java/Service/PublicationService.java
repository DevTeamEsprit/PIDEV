package Service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Employe;
import entity.Manager;
import entity.Publication;
import entity.Utilisateur;

/**
 * Session Bean implementation class PublicationService
 */
@Stateless
@LocalBean
public class PublicationService {

    /**
     * Default constructor. 
     */
	@PersistenceContext
    EntityManager em; 
	
	
	
    public PublicationService() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void addPublicaion(Publication p ) {
    	em.persist(p);
    }
    
    public void updatePublication(Publication p) {
    	em.merge(p);
    }
    
    public void deletePublication(long id_pub) {
    	em.remove(em.find(Publication.class,id_pub));
    }
    
    public List<Publication> listerPub(){
		TypedQuery<Publication> query = em.createQuery("select p from Publication p ORDER BY p.id ", 
				Publication.class);
		
		try {
		
			return query.getResultList();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
    }
    
    public List<Publication> getuserPub(Utilisateur u) {
    	TypedQuery<Publication> query = em.createQuery("select p from Publication p where p.user.id=:id", 
				Publication.class);
    	query.setParameter("id", u.getId());
		
		try {
			System.out.println(query.getResultList().size());
			return query.getResultList();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;    	
    }

}