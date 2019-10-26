package managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.UtilisateurService;
import Service.evaluation.EvaluationService;
import entity.*;

@ManagedBean
@SessionScoped
public class EvaluationBeanEmploye {
      private List<EvaluationSheet> evaluationsheets;
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
     
}
