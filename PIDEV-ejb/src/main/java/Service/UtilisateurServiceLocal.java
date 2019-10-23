package Service;

import java.util.List;

import javax.ejb.Local;

import entity.Employe;
import entity.Utilisateur;

@Local
public interface UtilisateurServiceLocal {

	public void addUser(Utilisateur user);
	public void updateEmploye(Employe employe);
	public void blockEmploye(long  idemploye);
	public List<Employe> consulterEmploye();
	public Utilisateur getUser(long id);
}
