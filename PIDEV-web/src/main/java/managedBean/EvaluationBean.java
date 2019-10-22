package managedBean;

import javax.ejb.EJB;
import javax.faces.bean.*;

import Service.evaluation.EvaluationService;

@ManagedBean
@SessionScoped
public class EvaluationBean {

	
	@EJB
	EvaluationService evaluationService;
	
	public String doShowEval() {
		return "evaluationAdmin.xhtml?faces-redirect=true";
	}
}
