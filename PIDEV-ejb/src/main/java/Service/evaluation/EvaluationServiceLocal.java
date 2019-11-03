
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
	public void activateEvaluation(int id);
	public List<Employe> getEmployeesOfManager(long manid);
	public Evaluation getLastEvaluation(long manid);
	public List<EvaluationSheet> findEvaluationSheetbyEval(int evalid);
	public Goal LastGoalByEval(int evalid);
	public void addGoal(Goal g);
	public void addGoalEmploye(GoalByEmploye gp);
	public List<EvaluationSheet> EvalsByEmploye(long empid);
	public List<GoalByEmploye> getGoalsOfEvals(int evaluationsheetid);
	public EvaluationSheet getEvSheetById(int id);
	public EvaluationSheet getEvSheetByEmpAndEval(int evalid,long empid);
	public void changeNoteGoal(GoalByEmploye g);
	public void switchSheetState(EvaluationSheet ev);
	public Evaluation getEvaluationBySheet(EvaluationSheet e);
	public void UpdateEvalSheet(EvaluationSheet e);
	public void CancelEvaluation(Evaluation e);
	public void DeleteGoal(int goalid);
	public void DeleteEvalSheets(int evalid);
	public List<Employe> findEmployeOrderByNote(int evalid);
	public List<GoalByEmploye> gempbyEmpAndEval(long empid , int evalid);

}