package Service.evaluation;

import javax.ejb.Remote;

import entity.Evaluation;

@Remote
public interface EvaluationServiceRemote {

	public Evaluation findEval(int id);
}
