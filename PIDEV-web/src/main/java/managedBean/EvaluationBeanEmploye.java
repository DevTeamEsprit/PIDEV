
package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.MessagingException;

import Service.UtilisateurService;
import Service.evaluation.EvaluationService;
import Service.evaluation.MailService;
import entity.*;
import io.undertow.server.session.Session;
import service.ServiceManager;

@ManagedBean
@SessionScoped
public class EvaluationBeanEmploye implements Serializable{
      private List<EvaluationSheet> evaluationsheets;
      
      @ManagedProperty(value = "#{loginbean}")
  	private Loginbean lb;
      public Loginbean getLb() {
  		return lb;
  	}

  	public void setLb(Loginbean lb) {
  		this.lb = lb;
  	}
      
      @Inject
  	private ServiceManager serviceManager;
      
      private int evalsheetid;
      private String emailText;
     // private MailService mailservice ;
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
		if (this.lb.getUser() == null) {
			try {
				this.serviceManager.goToPage("../login.jsf");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
     
	public void SendEmail() {
		 try {
	            MailService.sendMessage(utilisateurService.findManager(1).getEmail(),evsheet.getEvalsheetTitle() , emailText);
	            FacesContext.getCurrentInstance().addMessage("form:mail-btn", new FacesMessage("Message sent"));
	        }
	        catch(MessagingException ex) {
	        	System.out.println(ex.getMessage());
	        	//FacesContext.getCurrentInstance().addMessage("form:mail-btn", new FacesMessage("Error sending"));
	        }
		
	}
}