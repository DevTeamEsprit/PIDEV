package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="employe")
public class Employe extends Utilisateur implements Serializable {

	@OneToMany(mappedBy = "employe")
	private List<Ticket> tickets;

	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public Employe() {
		super();
	}
	
	public Employe(String nom, String prenom, String cin, String adresse, String tel, String email, String password,
			Date datNais) {
		super(nom, prenom, cin, adresse, tel, email, password, datNais);
		
	}



	
}
