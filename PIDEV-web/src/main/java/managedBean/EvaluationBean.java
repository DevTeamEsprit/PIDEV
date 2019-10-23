package managedBean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.*;

import Service.evaluation.EvaluationService;
import entity.EvalType;

@ManagedBean
@SessionScoped
public class EvaluationBean {
       private Date date;
       private EvalType evalType;
	
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
	
	public String doShowEval() {
		return "evaluationAdmin.xhtml?faces-redirect=true";
	}
}
