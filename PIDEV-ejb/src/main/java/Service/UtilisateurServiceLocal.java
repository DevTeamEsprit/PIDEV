package Service;

import java.util.List;

import javax.ejb.Local;

import entity.Employe;
import entity.Manager;

@Local
public interface UtilisateurServiceLocal {

	public void addEmploye(Employe employe);
	public void updateEmploye(Employe employe);
	public void blockEmploye(long  idemploye);
	public List<Employe> consulterEmploye();
	public Manager findManager(long id);
	
}
