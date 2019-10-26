package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="manager")
public class Manager extends Utilisateur implements Serializable{

	@OneToMany(mappedBy = "manager", cascade=CascadeType.PERSIST)
	public List<Employe> employes;

	
	
	public Manager() {
		super();
	}
	
 

	public Manager(long id, String nom, String prenom, String cin, String adresse, String tel, String email,
			String password, Date datNais) {
		super(id, nom, prenom, cin, adresse, tel, email, password, datNais);
		// TODO Auto-generated constructor stub
	}

	public Manager(String nom, String prenom, String cin, String adresse, String tel, String email, String password,
			Date datNais) {
		super(nom, prenom, cin, adresse, tel, email, password, datNais);
		// TODO Auto-generated constructor stub
	}

	@OneToMany(mappedBy="manager")
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	
}
