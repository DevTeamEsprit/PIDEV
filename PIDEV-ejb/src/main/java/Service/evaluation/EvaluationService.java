package Service.evaluation;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import entity.*;

/**
 * Session Bean implementation class EvaluationService
 */
@Stateless
@LocalBean
public class EvaluationService implements EvaluationServiceLocal,EvaluationServiceRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public EvaluationService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createEvaluation(Evaluation e) {
		em.persist(e);
		
	}

	@Override
	public void createEvaluationSheet(EvaluationSheet ev) {
		em.persist(ev);		
	}

	@Override
	public Evaluation findEval(int id) {
		// TODO Auto-generated method stub
		return em.find(Evaluation.class, id);
	}

	@Override
	public List<Evaluation> findByManager(long manid) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("select e from Evaluation e where e.manager.id =:id order by e.date asc");
		q.setParameter("id", manid);
		return q.getResultList();
	}

	@Override
	public List<Goal> findGoalsByEval(int evalId) {
		Query q = em.createQuery("select g from Goal g  where g.evaluation.id =:id");
		q.setParameter("id", evalId);
		if(q.getResultList().isEmpty())
		return null;
		
		return q.getResultList();
	}

	@Override
	public List<Employe> findEmployesByEval(int evalId) {
		Query q = em.createQuery("select e from EvaluationSheet e  where e.evaluation.id =:id");
		q.setParameter("id", evalId);
		if(q.getResultList().isEmpty())
		return null;
		
		return  q.getResultList();
	}

	@Override
	public boolean isAnnualExists(long manid) {
		Query q = em.createQuery("select e from Evaluation e  where e.manager.id =:id and e.type=:type");
		q.setParameter("id", manid);
		q.setParameter("type", EvalType.ANNUAL);
		
		return !q.getResultList().isEmpty();
	}

	@Override
	public void SwitchState(int id) {
      Evaluation e= em.find(Evaluation.class, id);
      if(e.isStatus())
    	  e.setStatus(false);
      else {
    	  e.setStatus(true);
    	  e.setDate(new Date());
      }
	}

	@Override
	public List<Employe> getEmployeesOfManager(long manid) {
		Query q = em.createQuery("select e from Employe e  where e.manager.id =:id");
		q.setParameter("id", manid);
		return q.getResultList();
	}

	@Override
	public Evaluation getLastEvaluation() {
		
		//return  em.createQuery("select e from Employe e  where e.manager.id =:id");
		return null;
	}
	


}
