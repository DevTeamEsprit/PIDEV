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
import entity.EvalType;
import entity.Evaluation;
import entity.Manager;

@ManagedBean
@SessionScoped
public class EvaluationBean {
       private Date date;
       private EvalType evalType;
       private String EvalTitle;
       private Manager manager;
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
			if(evalType.ordinal()!=3 ) {
				
				evaluationService.createEvaluation(e);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(e.getDate());
				this.setEvalTitle("Evaluation of "+ date +"(by "+manager.getNom()+" "+manager.getPrenom()+")");
				return "evaluationDetails.xhtml?faces-redirect=true";
			}
			
		} catch (NullPointerException e2) {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad credentials"));
			// TODO: handle exception
		}
		
		
		
		return "";
	}
	
	public String showEval() {
		
		this.setEvalTitle("Evaluation of "+date+"(by "+manager.getNom()+" "+manager.getPrenom()+")");
		return "evaluationDetails.xhtml?faces-redirect=true";
	}
	
	/*
	public List<Evaluation> evaluations(){
		evals = evaluationService.findByManager(1);
		return evals;
	}
	*/
}
