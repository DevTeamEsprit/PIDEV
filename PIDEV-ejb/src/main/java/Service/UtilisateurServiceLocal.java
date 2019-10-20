package Service;

import java.util.List;

import javax.ejb.Local;

import entity.Employe;

@Local
public interface UtilisateurServiceLocal {

	public void addEmploye(Employe employe);
	public void updateEmploye(Employe employe);
	public void blockEmploye(long  idemploye);
	public List<Employe> consulterEmploye();
	
}
