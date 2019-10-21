package Service.evaluation;

import javax.ejb.Remote;

import entity.*;

@Remote
public interface EvaluationServiceRemote {
     
	public void createEvaluation(Evaluation e);
	public void createEvaluationSheet(EvaluationSheet ev);
}
