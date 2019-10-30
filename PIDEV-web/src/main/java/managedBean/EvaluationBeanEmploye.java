
package managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.UtilisateurService;
import Service.evaluation.EvaluationService;
import entity.*;
import io.undertow.server.session.Session;

@ManagedBean
@SessionScoped
public class EvaluationBeanEmploye {
      private List<EvaluationSheet> evaluationsheets;
      
     
      private int evalsheetid;
      private String emailText;
      private Session mailSession;
      public Session getMailSession() {
		return mailSession;
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}

	public String getEmailText() {
		return emailText;
	}

	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}

	private EvaluationSheet evsheet;
      private List<Integer> notes;
      private String submitvalue;
      public String appreciation;
      
      public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public String getSubmitvalue() {
		return submitvalue;
	}

	public void setSubmitvalue(String submitvalue) {
		this.submitvalue = submitvalue;
	}

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
       for(EvaluationSheet e :  evaluationsheets) {
    	   e.setEvalsheetTitle("Evaluation of "+ evaluationService.getEvaluationBySheet(e).getDate());
		 }
       System.out.println(evaluationsheets.size());
       }
	
	public List<Integer> getNotes() {
		return notes;
	}

	public void setNotes(List<Integer> notes) {
		this.notes = notes;
	}

	public String showEvalSheet() {
		 goalByEvalsheet = evaluationService.getGoalsOfEvals(evalsheetid);
		
		 evsheet = evaluationService.getEvSheetById(evalsheetid);
		
		 if(evsheet.isStatus()==true)
			 submitvalue = "Change";
		 else
			 submitvalue = "Submit";
		 
		 if(evsheet.getAppreciation().equalsIgnoreCase(""))
			 appreciation ="Waiting for  validation";
		 else
			 appreciation = evsheet.getAppreciation();
		 
		return "evaluationSheetEmploye.xhtml?faces-redirect=true";
	}
	
	public String submitSheet() {
		if(submitvalue.equalsIgnoreCase("Submit")) {
		for(GoalByEmploye g : goalByEvalsheet) {
			evaluationService.changeNoteGoal(g);
		}
		evaluationService.switchSheetState(evsheet);
		 submitvalue = "Change";
		 goalByEvalsheet = evaluationService.getGoalsOfEvals(evalsheetid);
			
		 evsheet = evaluationService.getEvSheetById(evalsheetid);
		 return "evaluationSheetEmploye.xhtml?faces-redirect=true";
		}
		else {
			evaluationService.switchSheetState(evsheet);
		submitvalue = "Submit";
		}
		return null;
	}
     
}