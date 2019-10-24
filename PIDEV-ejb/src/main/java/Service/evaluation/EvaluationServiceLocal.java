package Service.evaluation;

import java.util.List;

import javax.ejb.Local;

import entity.*;

@Local
public interface EvaluationServiceLocal {
     
	public void createEvaluation(Evaluation e);
	public void createEvaluationSheet(EvaluationSheet ev);
	public Evaluation findEval(int id);
	public List<Evaluation> findByManager(long manid);
}
