package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class EvaluationSheet implements Serializable {

	@EmbeddedId
	EvaluationSheetId pk;
	@ManyToOne
	@JoinColumn(name="id",insertable=false,updatable=false)
	private Employe employe;
	
	@ManyToOne
	@JoinColumn(name="id",insertable=false,updatable=false)
	private Evaluation evaluation;
	

	public EvaluationSheetId getPk() {
		return pk;
	}

	public EvaluationSheet(EvaluationSheetId pk) {
		super();
		this.pk = pk;
	}

	public EvaluationSheet(EvaluationSheetId pk, Employe employe, Evaluation evaluation) {
		super();
		this.pk = pk;
		this.employe = employe;
		this.evaluation = evaluation;
	}

	public void setPk(EvaluationSheetId pk) {
		this.pk = pk;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	
	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	
	
}
