package Service.mission;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Service.UtilisateurService;
import entity.Employe;
import entity.Mission;
import entity.Utilisateur;

@Stateless
public class MissionService implements MissionServiceRemote, MissionServiceLocal {
	@PersistenceContext
	private EntityManager em;

    public MissionService() {
    }

	public void create(Mission t) {
		em.persist(t);
	}

	
	
	@Override
	public void update(Mission t) {
		em.merge(t);
	}
	
	
	@Override
	public void delete(int id) {
		
		Mission t = em.find(Mission.class,id);
		em.remove(t);
		
	}

	@Override
	public List<Mission> showALL() {
		List<Mission> emp = em.createQuery("Select e from Mission e",Mission.class).getResultList();
				return emp;

	}						

	@Override
	public List<Mission> showEmpMission(int id) {
TypedQuery<Mission> query = em.createQuery("select m from Mission m where m.idemp='id' ", Mission.class);
		
		try {
			return query.getResultList();
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}		return null;
	}

	@Override
	public int updatestat(int id,Mission e) {
		return em.createQuery("update Mission u set u.location='"+e.getLocalisation()+"' , u.duration='"+e.getDuration()+"' where u.id="+id).executeUpdate();	
	}

	@Override
	public List<Mission> search(String s) {
TypedQuery<Mission> query = em.createQuery("select m from Mission m where m.idemp=:id ", Mission.class);
		
		try {
			return query.getResultList();
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}		return null;
	}

	
	

	
	


}

