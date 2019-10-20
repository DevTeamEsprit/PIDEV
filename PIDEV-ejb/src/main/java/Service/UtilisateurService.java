package Service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entity.Employe;

/**
 * Session Bean implementation class UtilisateurService
 */
@Stateless
@LocalBean
public class UtilisateurService implements UtilisateurServiceLocal {

    /**
     * Default constructor. 
     */
    public UtilisateurService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addEmplyee(Employe employe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmplyee(Employe employe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blockEmplyee(Employe employe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employe> consulterEmploye() {
		// TODO Auto-generated method stub
		return null;
	}

}
