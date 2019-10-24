package Service;

import javax.ejb.Remote;

import entity.Manager;

@Remote
public interface UtilisateurServiceRemote {
	public Manager findManager(long id);
}
