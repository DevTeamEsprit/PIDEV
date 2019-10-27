package Service.Timesheet;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Employe;
import entity.Project;
import entity.Ticket;


@Stateless
public class ProjectService implements ProjectServiceLocal, ProjectServiceRemote {
	
	@PersistenceContext
	private EntityManager em;
	
	

    public ProjectService() {
    }

	public void createp(Project p) {
		em.persist(p);
	}

	public List<Project> findAll() {
		return em.createQuery("from Project", Project.class).getResultList();
	}
	
	@Override
	public void updatep(Project p) {
		em.merge(p);
	}
	
	@Override
	public void deletep(int id) {		
		Project p = em.find(Project.class, id);

		em.remove(p);		
	}

	@Override
	public Project getById(int id) {
		return em.find(Project.class, id);
	}


}
