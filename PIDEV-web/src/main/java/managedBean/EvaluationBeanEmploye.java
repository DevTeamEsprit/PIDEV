package managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Service.UtilisateurService;
import Service.evaluation.EvaluationService;
import entity.*;

@ManagedBean
@SessionScoped
public class EvaluationBeanEmploye {
      private List<EvaluationSheet> evaluationsheets;
      
     
      private int evalsheetid;
      private EvaluationSheet evsheet;
      
      public EvaluationSheet getEvsheet() {
		return evsheet;
	}

	public void setEvsheet(EvaluationSheet evsheet) {
		this.evsheet = evsheet;
	}

	private List<GoalByEmploye> goalByEvalsheet;
	public List<GoalByEmploye> getGoalByEvalsheet() {
		return goalByEvalsheet;
	}

	public void setGoalByEvalsheet(List<GoalByEmploye> goalByEvalsheet) {
		this.goalByEvalsheet = goalByEvalsheet;
	}

	public int getEvalsheetid() {
		return evalsheetid;
	}

	public void setEvalsheetid(int evalsheetid) {
		this.evalsheetid = evalsheetid;
	}

	public List<EvaluationSheet> getEvaluationsheets() {
		return evaluationsheets;
	}

	public void setEvaluationsheets(List<EvaluationSheet> evaluationsheets) {
		this.evaluationsheets = evaluationsheets;
	}

	@EJB
	EvaluationService evaluationService;
	
	@EJB
	UtilisateurService utilisateurService;
	
	@PostConstruct
    public void init() {
       evaluationsheets = evaluationService.EvalsByEmploye(2);
     
       }
	
	public String showEvalSheet() {
		 goalByEvalsheet = evaluationService.getGoalsOfEvals(evalsheetid);
		 evsheet = evaluationService.getEvSheetById(evalsheetid);
		return "evaluationSheetEmploye.xhtml?faces-redirect=true";
	}
     
}
