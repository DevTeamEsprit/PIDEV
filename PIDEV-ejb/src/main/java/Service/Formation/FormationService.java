package Service.Formation;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Commentaire;
import entity.Formation;
import entity.Publication;

/**
 * Session Bean implementation class FormationService
 */
@Stateless
@LocalBean
public class FormationService {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
    EntityManager em; 
	
	
    public FormationService() {
        // TODO Auto-generated constructor stub
    }

 
    
    public void addFormation(Formation f) {
    	em.persist(f);
    }
    
    
   public List<Formation> listerForamtion(){
	   TypedQuery<Formation> query = em.createQuery("select f from Formation f ", 
			   Formation.class);
		
		try {
		
			return query.getResultList();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	  
   }
}
