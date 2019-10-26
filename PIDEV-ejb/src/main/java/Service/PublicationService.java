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
    	Utilisateur u =em.find(Utilisateur.class,1L);
    	if(u instanceof Employe)
    		p.setUser((Employe)u);
    	else
    		p.setUser((Manager)u);
    	em.persist(p);
    }
    
    public void updatePublication(Publication p) {
    	em.merge(p);
    }
    
    public void deletePublication(long id_pub) {
    	em.remove(em.find(Publication.class,id_pub));
    }
    
    public List<Publication> listerPub(){
		TypedQuery<Publication> query = em.createQuery("select p from Publication p ORDER BY p.dateCreation  DESC", 
				Publication.class);
		
		try {
			System.out.println(query.getResultList().size());
			return query.getResultList();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
    }
    
    public Utilisateur getuserPub(long id) {
    	TypedQuery<Publication> query = em.createQuery("select p.user from Publication p where id=5", 
				Publication.class);
		
		try {
			System.out.println(query.getResultList().size());
			return query.getSingleResult().getUser();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;    	
    }

}
