package Service.mission;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Mission;
import entity.resultatMission;

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
		List<Mission> emp = em.createQuery("select m from Mission m where m.idemp="+id+"",Mission.class).getResultList();
		return emp;	}

	@Override
	public int updatestat(int id,Mission e) {
		return em.createQuery("update Mission u set u.location='"+e.getLocalisation()+"' , u.duration='"+e.getDuration()+"' where u.id="+id).executeUpdate();	
	}
	
	@Override
	public List<Mission> search(resultatMission val) {
		List<Mission> emp = em.createQuery("select m from Mission m where m.resultat LIKE :custResultat ",Mission.class).setParameter("custResultat", val).getResultList();
		return emp;	}
	
	
	
	@Override
	public void updateAcce(int id) {
		Mission m = em.find(Mission.class, id);
		m.setResultat(resultatMission.PRGRESSE);
		em.merge(m);
	}
	@Override
	public void updateFail(int id) {
		Mission m = em.find(Mission.class, id);
		m.setResultat(resultatMission.FAIL);
		em.merge(m);
	}
	
	public void updateSuc(int id) {
		Mission m = em.find(Mission.class, id);
		m.setResultat(resultatMission.SUCSCESS);
		em.merge(m);
	}
	}

	
	

	
	




