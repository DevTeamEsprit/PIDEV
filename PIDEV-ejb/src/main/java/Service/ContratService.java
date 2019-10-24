package Service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Employe;
import entity.Publication;

/**
 * Session Bean implementation class ContratService
 */
@Stateless
@LocalBean
public class ContratService {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
    EntityManager em; 
	
    public ContratService() {
        // TODO Auto-generated constructor stub
    }
    
   
    
}
