package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="Employe")
public class Employe extends Utilisateur implements Serializable {

	
	public Employe() {
		super();
	}
	
	public Employe(String nom, String prenom, String cin, String adresse, String tel, String email, String password,
			Date datNais) {
		super(nom, prenom, cin, adresse, tel, email, password, datNais);
		
	}



	
}
