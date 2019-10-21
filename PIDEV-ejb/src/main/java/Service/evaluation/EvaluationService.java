package Service.evaluation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import entity.*;

/**
 * Session Bean implementation class EvaluationService
 */
@Stateless
@LocalBean
public class EvaluationService implements EvaluationServiceRemote {

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



}
