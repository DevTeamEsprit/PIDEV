package Service.mission;

import java.util.List;

import javax.ejb.Remote;

import entity.Mission;
import entity.Utilisateur;

@Remote
public interface MissionServiceRemote {

	void create(Mission M);
	List<Mission> showALL();
	void update(Mission M);
	void delete(int id);
	List<Mission> showEmpMission(int id);
	List<Mission> search(String s);
    int updatestat(int id,Mission M);
}

