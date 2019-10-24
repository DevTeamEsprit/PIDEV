package Service.evaluation;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Evaluation findEval(int id) {
		// TODO Auto-generated method stub
		return em.find(Evaluation.class, id);
	}

	@Override
	public List<Evaluation> findByManager(long manid) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("select e from Evaluation e where e.manager.id =:id");
		q.setParameter("id", manid);
		return q.getResultList();
	}



}
