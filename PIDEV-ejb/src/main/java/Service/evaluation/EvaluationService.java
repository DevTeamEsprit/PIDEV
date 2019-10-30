
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
		Query q = em.createQuery("select e from Evaluation e where e.manager.id =:id ");
		q.setParameter("id", manid);
		if(!q.getResultList().isEmpty())
			return q.getResultList();
			
			return null;
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
		Query q = em.createQuery("select e from Employe e where e.id  in (select g.employe.id from GoalByEmploye g  join g.goal go join go.evaluation e where e.id=:id)");
		q.setParameter("id", evalId);
		if(!q.getResultList().isEmpty())
		
		return  q.getResultList();

		return null;
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
		if(!q.getResultList().isEmpty())
			return q.getResultList();
			
			return null;
	}

	@Override
	public Evaluation getLastEvaluation(long manid) {
		 Query q = em.createQuery("select e from Evaluation e where e.manager.id=:id order by e.id desc",Evaluation.class);
		q.setParameter("id", manid);
		List<Evaluation> evals = q.getResultList();
		if(!q.getResultList().isEmpty())
			return evals.get(0);
			
			return null;
		
	}

	@Override
	public List<EvaluationSheet> findEvaluationSheetbyEval(int evalid) {
		Query q = em.createQuery("select e from EvaluationSheet e where e.id in (select ge.evaluationSheet.id from GoalByEmploye ge  join ge.goal g join g.evaluation e where e.id=:id )",EvaluationSheet.class);
		q.setParameter("id", evalid);
		if(!q.getResultList().isEmpty())
			return q.getResultList();
			
			return null;
	}

	@Override
	public Goal LastGoalByEval(int evalid) {
		 Query q = em.createQuery("select e from Goal e where e.evaluation.id=:id order by e.id desc",Goal.class);
			q.setParameter("id", evalid);
			List<Goal> goals = q.getResultList();
			
			if(!goals.isEmpty())
			return goals.get(0);
			
			return null;
	}

	@Override
	public void addGoal(Goal g) {
		em.persist(g);
		
	}

	@Override
	public void addGoalEmploye(GoalByEmploye gp) {
		em.persist(gp);
		
	}

	@Override
	public List<EvaluationSheet> EvalsByEmploye(long empid) {
		Query q = em.createQuery("select e from EvaluationSheet e where e.id in (select ge.evaluationSheet.id from GoalByEmploye ge join ge.goal g join g.evaluation ev  where ge.employe.id=:id and ev.status=:status)",EvaluationSheet.class);
		q.setParameter("id", empid);
		q.setParameter("status", true);
		if(!q.getResultList().isEmpty())
		return q.getResultList();
		
		return null;
	}

	@Override
	public List<GoalByEmploye> getGoalsOfEvals(int evaluationsheetid) {
		Query q = em.createQuery("select g from GoalByEmploye g where g.evaluationSheet.id=:id )",GoalByEmploye.class);
		q.setParameter("id", evaluationsheetid);
		if(!q.getResultList().isEmpty())
			return q.getResultList();
			
			return null;
	}

	@Override
	public void activateEvaluation(int id) {
		 Evaluation e= em.find(Evaluation.class, id);
		 e.setStatus(true);
		
	}

	@Override
	public EvaluationSheet getEvSheetById(int id) {
		// TODO Auto-generated method stub
		return em.find(EvaluationSheet.class, id);
				
	}

	@Override
	public void changeNoteGoal(GoalByEmploye g) {
	    em.merge(g);
	}

	@Override
	public void switchSheetState(EvaluationSheet ev) {
		EvaluationSheet e = em.find(EvaluationSheet.class, ev.getId());
		if(e.isStatus()==true)
			e.setStatus(false);
		else
			e.setStatus(true);
		
	}

	@Override
	public Evaluation getEvaluationBySheet(EvaluationSheet e) {
		TypedQuery<Evaluation> q = em.createQuery("select e from Evaluation e where e.id in (select go.evaluation.id from Goal go where go.id in (select ge.pk.GoalId from GoalByEmploye ge where ge.evaluationSheet =:ev))",Evaluation.class);
		q.setParameter("ev", e);
		return q.getSingleResult();
	}

	@Override
	public EvaluationSheet getEvSheetByEmpAndEval(int evalid, long empid) {
		TypedQuery<EvaluationSheet> q = em.createQuery("select es from EvaluationSheet es where es.id in (select ge.evaluationSheet.id from GoalByEmploye ge join ge.goal g join g.evaluation ev where ev.id=:evalid and ge.pk.employeId=:empid)",EvaluationSheet.class);
		q.setParameter("evalid", evalid);
		q.setParameter("empid", empid);
		return q.getSingleResult();
	}

	@Override
	public void UpdateEvalSheet(EvaluationSheet e) {
		em.merge(e);
		
	}

	@Override
	public void CancelEvaluation(Evaluation e) {
		
		List<EvaluationSheet> evaluations = this.findEvaluationSheetbyEval(e.getId());
		
		if(evaluations !=null) {
		for(EvaluationSheet es : evaluations) {
			EvaluationSheet esv =em.find(EvaluationSheet.class, es.getId());
			em.remove(esv);
		}
		}
		Evaluation e2 =em.find(Evaluation.class, e.getId());
		em.remove(e2);
	}
}
	
