package Service.Formation;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.DemandeFormation;
import entity.Formation;

@Stateless
@LocalBean
public class DemandeFormationService implements Serializable {
	@PersistenceContext
    EntityManager em; 
	
	
    public void addFormation(DemandeFormation d) {
    	em.persist(d);
    }
    
    
    
   public List<DemandeFormation> listerForamtion(){
	   TypedQuery<DemandeFormation> query = em.createQuery("select f from DemandeFormation f ", 
			   DemandeFormation.class);
		
		try {
		
			return query.getResultList();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	  
   }
	
}
