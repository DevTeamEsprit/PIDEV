package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Employe extends Utilisateur implements Serializable {

	public Employe(long id, String nom, String prenom, String cin, String adresse, String tel, String email,
			String password, Date datNais) {
		super(id, nom, prenom, cin, adresse, tel, email, password, datNais);
		// TODO Auto-generated constructor stub
	}
	
}
