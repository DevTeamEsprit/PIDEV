package managedBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import Service.UtilisateurService;
import Service.evaluation.EvaluationService;
import entity.*;

@ManagedBean
@SessionScoped
public class EvaluationBean {
	   private int evalId;
       public int getEvalId() {
		return evalId;
	}

	public void setEvalId(int evalId) {
		this.evalId = evalId;
	}

	private Date date;
       private EvalType evalType;
       private String EvalTitle;
       private Manager manager;
       private boolean status;
       public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Evaluation> evals;
       
	
	public List<Evaluation> getEvals() {
		return evals;
	}

	public void setEvals(List<Evaluation> evals) {
		this.evals = evals;
	}

	

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getEvalTitle() {
		return EvalTitle;
	}

	public void setEvalTitle(String evalTitle) {
		EvalTitle = evalTitle;
	}

	public EvalType getEvalType() {
		return evalType;
	}

	public void setEvalType(EvalType evalType) {
		this.evalType = evalType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@EJB
	EvaluationService evaluationService;
	
	@EJB
	UtilisateurService utilisateurService;
	

	@PostConstruct
    public void init() {
        evals = evaluationService.findByManager(1);
        manager = utilisateurService.findManager(1);
       }

	public String addEval() throws ParseException {
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		date = dateformat.parse("2019/12/25");*/
		
		Evaluation e = new Evaluation(0, evalType, date,manager,false);
		try {
			if(evalType.ordinal()!=0  ) {
				if((new Date()).compareTo(date)<0) {
				if(evalType.ordinal()==2 && evaluationService.isAnnualExists(1)) {
					FacesContext.getCurrentInstance().addMessage("form:btn-addeval", new FacesMessage("Annual evaluation already programmed by you"));
				}
				else {
					
				evaluationService.createEvaluation(e);
				if(evalType.ordinal()==2) {
					for(Employe emp : evaluationService.getEmployeesOfManager(1)) {
						EvaluationSheetId pk = new EvaluationSheetId(emp.getId(), evalId, "", "", false);
						EvaluationSheet ev = new EvaluationSheet(pk);
						evaluationService.createEvaluationSheet(ev);
					}
					}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(e.getDate());
				this.setStatus(e.isStatus());
				this.setEvalTitle("Evaluation of "+ date +"(by "+manager.getNom()+" "+manager.getPrenom()+")");
				return "evaluationDetails.xhtml?faces-redirect=true";
				}
				}
				else
					FacesContext.getCurrentInstance().addMessage("form:btn-addeval", new FacesMessage("Please select a valid date!(after tomorrow)"));
			}
			
		} catch (NullPointerException e2) {
			FacesContext.getCurrentInstance().addMessage("form:btn-addeval", new FacesMessage("Bad credentials"));
			// TODO: handle exception
		}
		
		
		
		return "";
	}
	
	public String showEval() {
		
		this.setEvalTitle("Evaluation of "+date+"(by "+manager.getNom()+" "+manager.getPrenom()+")");
		return "evaluationDetails.xhtml?faces-redirect=true";
	}
	
     public void SwitchEvaluation() {
    	 
    	if(evaluationService.findEmployesByEval(evalId)==null) {
    		FacesContext.getCurrentInstance().addMessage("form1:btn-eval", new FacesMessage("No employee affected!"));
    	}
    	
        if(evaluationService.findGoalsByEval(evalId)==null) {
        	FacesContext.getCurrentInstance().addMessage("form1:btn-eval", new FacesMessage("No goal affected!"));
    	}
        else {
        	evaluationService.SwitchState(evalId);
        	status = evaluationService.findEval(evalId).isStatus();
        	if(status)
        		FacesContext.getCurrentInstance().addMessage("form1:btn-eval", new FacesMessage("Evaluation switched off"));
        	else
        		FacesContext.getCurrentInstance().addMessage("form1:btn-eval", new FacesMessage("Evaluation triggered"));
        }
     }
}
