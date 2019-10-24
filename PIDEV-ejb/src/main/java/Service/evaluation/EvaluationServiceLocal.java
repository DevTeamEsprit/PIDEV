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
	public List<Goal> findGoalsByEval(int evalId);
	public List<Employe> findEmployesByEval(int evalId);
	public boolean isAnnualExists(long manid);
	public void SwitchState(int id);
	public List<Employe> getEmployeesOfManager(long manid);
	public Evaluation getLastEvaluation();
}
