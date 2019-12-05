package Service.mission;

import java.util.List;

import javax.ejb.Remote;

import entity.Mission;
import entity.Utilisateur;
import entity.resultatMission;

@Remote
public interface MissionServiceRemote {

	void create(Mission M);
	List<Mission> showALL();
	void update(Mission M);
	void delete(int id);
	List<Mission> showEmpMission(int id);
	List<Mission> search(resultatMission val);
    int updatestat(int id,Mission M);
    void updateAcce(int id);
    void updateFail(int id);
	void updateSuc(int id);


}

