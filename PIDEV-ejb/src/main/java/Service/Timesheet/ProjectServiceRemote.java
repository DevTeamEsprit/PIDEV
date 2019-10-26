package Service.Timesheet;

import java.util.List;

import javax.ejb.Remote;

import entity.Project;
import entity.Ticket;

@Remote
public interface ProjectServiceRemote {

	void createp(Project p);
	List<Project> findAll();
	Project getById(int id);
	void updatep(Project p);
	void deletep(int id);

}
