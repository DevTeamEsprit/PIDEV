

package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import Service.UtilisateurService;
import Service.evaluation.EvaluationService;
import entity.*;
import service.ServiceManager;

@ManagedBean
@SessionScoped
public class EvaluationBean implements Serializable{
	   private int evalId;
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
	   private int goalid;
       public int getGoalid() {
		return goalid;
	}

	public void setGoalid(int goalid) {
		this.goalid = goalid;
	}

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
       public Evaluation evaluation;
       public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	private long empId;
       private String appreciation;
       public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	private GoalType goaltype;
	private List<GoalByEmploye> goalsBySheet;
       public List<GoalByEmploye> getGoalsBySheet() {
		return goalsBySheet;
	}

	public void setGoalsBySheet(List<GoalByEmploye> goalsBySheet) {
		this.goalsBySheet = goalsBySheet;
	}

	private String goalext;
       private List<Goal> goals;
       private EvaluationSheet evalSheet;
       private List<EvaluationSheet> sheets ;
       public List<EvaluationSheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<EvaluationSheet> sheets) {
		this.sheets = sheets;
	}

	public EvaluationSheet getEvalSheet() {
		return evalSheet;
	}

	public void setEvalSheet(EvaluationSheet evalSheet) {
		this.evalSheet = evalSheet;
	}

	private List<Employe> employes;
       public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public GoalType getGoaltype() {
		return goaltype;
	}

	public void setGoaltype(GoalType goaltype) {
		this.goaltype = goaltype;
	}

	public String getGoalext() {
		return goalext;
	}

	public void setGoalext(String goalext) {
		this.goalext = goalext;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
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
		if (this.lb.getUser() == null) {
			try {
				this.serviceManager.goToPage("../login.jsf");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        evals = evaluationService.findByManager(1);
        manager = utilisateurService.findManager(1);
        
        for(Evaluation e : evals) {
        	if((new Date()).compareTo(e.getDate())==0)
           evaluationService.activateEvaluation(e.getId());
        }
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
				Evaluation LastEval = evaluationService.getLastEvaluation(1);
				evalId = LastEval.getId();
				EvaluationService.evalid=evalId;
				goals = evaluationService.findGoalsByEval(evaluationService.findEval(evalId).getId());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(e.getDate());
				this.setStatus(e.isStatus());
				this.setEvalTitle("Evaluation of "+ date +"(by "+manager.getNom()+" "+manager.getPrenom()+")");
				employes = evaluationService.findEmployesByEval(evalId);
				evaluation = evaluationService.getLastEvaluation(1);
				evals = evaluationService.findByManager(1);
				return "evaluationDetails.xhtml?faces-redirect=true";
				}
				}
				else
					FacesContext.getCurrentInstance().addMessage("form:btn-addeval", new FacesMessage("Please select a valid date!(after today)"));
			}
			
		} catch (NullPointerException e2) {
			FacesContext.getCurrentInstance().addMessage("form:btn-addeval", new FacesMessage("Bad credentials"));
			// TODO: handle exception
		}
		
		
		
		return "";
	}
	
	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public String showEval() {
		goals = evaluationService.findGoalsByEval(evaluationService.findEval(evalId).getId());
		employes = evaluationService.findEmployesByEval(evalId);
		evaluation = evaluationService.findEval(evalId);
		EvaluationService.evalid=evalId;
		if((new Date()).compareTo(evaluation.getDate())==0) {
	           evaluationService.activateEvaluation(evaluation.getId());
	        }
		sheets = evaluationService.findEvaluationSheetbyEval(evalId);
		this.setEvalTitle(evaluation.getType()+" evaluation of "+date+"(by "+manager.getNom()+" "+manager.getPrenom()+")");
		return "evaluationDetails.xhtml?faces-redirect=true";
	}
	
     public void SwitchEvaluation() {
    	
        if(evaluationService.findGoalsByEval(evalId)==null) {
        	FacesContext.getCurrentInstance().addMessage("form1:btn-eval", new FacesMessage("No goal affected!"));
    	}
        else {
        	evaluationService.SwitchState(evalId);
        	status = evaluationService.findEval(evalId).isStatus();
        	if(status) {
        		FacesContext.getCurrentInstance().addMessage("form1:btn-eval", new FacesMessage("Evaluation triggered"));
        	    for(EvaluationSheet e : sheets) {
        	    	e.setEvalsheetTitle(evaluation.getType()+" evaluation of "+date);
        	    }
        	}
        	else
        		FacesContext.getCurrentInstance().addMessage("form1:btn-eval", new FacesMessage("Evaluation switched off"));
        		
        }
     }
     
     public String addGoal() {
    	 if(!goalext.equalsIgnoreCase("") && goaltype!=null) {
    		 Goal Lastgoal = evaluationService.LastGoalByEval(evalId);
    		 evaluationService.addGoal(new Goal(0, goaltype, goalext, evaluationService.findEval(evalId)));
    		 if(Lastgoal!=null) {
    			 List<Employe> employesbyman = new ArrayList<Employe>();
    			 for(Employe e : evaluationService.getEmployeesOfManager(1)) {
    				 employesbyman.add(e);
    			 }
    			 int k = 0;
    			 for(EvaluationSheet i : evaluationService.findEvaluationSheetbyEval(evalId)) {
    				 System.out.println("Feuille d'evaluation trouvee");
    				 GoalByEmployeId pk = new GoalByEmployeId(employesbyman.get(k).getId(), evaluationService.LastGoalByEval(evalId).getId());
    				 GoalByEmploye gemp = new GoalByEmploye(pk,0,0);
    				 gemp.setEvaluationSheet(i);
    				 evaluationService.addGoalEmploye(gemp);
    				 k++;
    			 }
    			 FacesContext.getCurrentInstance().addMessage("form1:btn-goal", new FacesMessage("Goal added!"));
    			 goals = evaluationService.findGoalsByEval(evaluationService.findEval(evalId).getId());
    				employes = evaluationService.findEmployesByEval(evalId);
    				sheets = evaluationService.findEvaluationSheetbyEval(evalId);
        		 return "evaluationDetails.xhtml?faces-redirect=true";
    		 }
    		 else {
    			
    			 for(Employe e : evaluationService.getEmployeesOfManager(1)) {
    				 EvaluationSheet ev = new EvaluationSheet(0, false,evaluationService.findEval(evalId).getType().name()+" evaluation of "+ evaluationService.findEval(evalId).getDate(), "");
    				 GoalByEmployeId pk = new GoalByEmployeId(e.getId(), evaluationService.LastGoalByEval(evalId).getId());
    				 ev.addGoalEmploye(new GoalByEmploye(pk,0,0));
    				 evaluationService.createEvaluationSheet(ev);
    				 
    			 }
    			 
    			 FacesContext.getCurrentInstance().addMessage("form1:btn-goal", new FacesMessage("Goal added!"));
    			 goals = evaluationService.findGoalsByEval(evaluationService.findEval(evalId).getId());
    				employes = evaluationService.findEmployesByEval(evalId);
    				sheets = evaluationService.findEvaluationSheetbyEval(evalId);
        		 return "evaluationDetails.xhtml?faces-redirect=true";
    		 }
    		
    	 }    
    	 else
    		 FacesContext.getCurrentInstance().addMessage("form1:btn-goal", new FacesMessage("Fill correctly!"));
    	 return "evaluationDetails.xhtml?faces-redirect=true";
     }
     
     public String showEmpSheet(){
    	          evalSheet = evaluationService.getEvSheetByEmpAndEval(evalId, empId);
    	          evalSheet.setEvalsheetTitle(evaluation.getType()+" evaluation of "+evaluationService.findEval(evalId).getDate());
    	          goalsBySheet = evaluationService.getGoalsOfEvals(evalSheet.getId());
    	          appreciation = evalSheet.getAppreciation();
    	 return "evaluationSheetMan.xhtml?faces-redirect=true";
    	        
     }
     
     public String validate() {
    	    if(appreciation == null)
    	    	FacesContext.getCurrentInstance().addMessage("form:btn-val", new FacesMessage("Give an appreciation!"));
    	    else {
    	    	for(GoalByEmploye g : goalsBySheet) {
    				evaluationService.changeNoteGoal(g);
    			}
    	    	
    	    	evalSheet.setAppreciation(appreciation);
    	    	evaluationService.UpdateEvalSheet(evalSheet);
    	    	evalSheet = evaluationService.getEvSheetByEmpAndEval(evalId, empId);
    	    	  goalsBySheet = evaluationService.getGoalsOfEvals(evalSheet.getId());
    	    	FacesContext.getCurrentInstance().addMessage("form:btn-val", new FacesMessage("Submitted"));
    	    	 return "evaluationSheetMan.xhtml?faces-redirect=true";
    	    }
    	    return null;
     }
     
     public String  delete() {
    	 evaluationService.CancelEvaluation(evaluation);
    	 
    	 evals = evaluationService.findByManager(1);
    	 return "evaluationsCenter.xhtml?faces-redirect=true";
    	 
    	
     }
     
     public String RemoveGoal() {
		 if(evaluation.isStatus()) {
			 FacesContext.getCurrentInstance().addMessage("form:btn-del", new FacesMessage("Turn evaluation down first!"));
		 }
		 
		 
		 else {
			 evaluationService.DeleteGoal(goalid);
			 goals = evaluationService.findGoalsByEval(evaluationService.findEval(evalId).getId());
			 if(goals == null) {
				 evaluationService.DeleteEvalSheets(evalId);
			 }
			 
			 employes = evaluationService.findEmployesByEval(evalId);
				sheets = evaluationService.findEvaluationSheetbyEval(evalId);
			 return "evaluationDetails.xhtml?faces-redirect=true";
		 }
		 return null;
	 }
     
     public void FormWrite() {
    	 FacesContext.getCurrentInstance().addMessage("form:btn-del", new FacesMessage("Turn evaluation down first!"));
     }
}