package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue(value="employe")
public class Employe extends Utilisateur implements Serializable {

	@ManyToOne
//	@JsonManagedReference
	private Manager manager;
	
	
	public Employe() {
		super();
	}
	
	public Employe(String nom, String prenom, String cin, String adresse, String tel, String email, String password,
			Date datNais) {
		super(nom, prenom, cin, adresse, tel, email, password, datNais);
		
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}



	
}
