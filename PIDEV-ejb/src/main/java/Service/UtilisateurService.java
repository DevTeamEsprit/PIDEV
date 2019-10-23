package Service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Employe;
import entity.Manager;
 

/**
 * Session Bean implementation class UtilisateurService
 */
@Stateless
@LocalBean
public class UtilisateurService implements UtilisateurServiceLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
    EntityManager em; 
	
	
    public UtilisateurService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addEmploye(Employe employe) {
		em.persist(employe);
		
	}

	@Override
	public void updateEmploye(Employe employe) {
		em.merge(employe);
	}

	@Override
	public void blockEmploye(long  idemploye){
		Employe emp=em.find(Employe.class,idemploye);
		emp.setActif(false);
		em.merge(emp);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employe> consulterEmploye() {
			TypedQuery<Employe> query = em.createQuery("select e from Employe e ", Employe.class);
		
		try {
			return query.getResultList();
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		return null;
	}

	@Override
	public Manager findManager(long id) {
		TypedQuery<Manager> query = em.createQuery("select e from Manager e  where e.id=:id", Manager.class);
		     query.setParameter("id", id);
		return query.getSingleResult();
	}

}
