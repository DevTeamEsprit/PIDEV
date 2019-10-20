package Service;

import java.util.List;

import javax.ejb.Local;

import entity.Employe;

@Local
public interface UtilisateurServiceLocal {

	public void addEmplyee(Employe employe);
	public void updateEmplyee(Employe employe);
	public void blockEmplyee(Employe employe);
	public List<Employe> consulterEmploye();
	
}
