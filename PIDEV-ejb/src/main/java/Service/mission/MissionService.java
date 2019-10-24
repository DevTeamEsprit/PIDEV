package Service.mission;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Mission;

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
		
		Mission t = em.find(Mission.class, id);
		em.remove(t);
		
	}

	@Override
	public List<Mission> showALL() {
TypedQuery<Mission> query = em.createQuery("select m from Mission m", Mission.class);
		
		try {
			return query.getResultList();
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}		return null;
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
	public void updatestat(int id) {
		Mission mis=em.find(Mission.class,id);
		mis.setStat(true);
		em.merge(mis);		
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

