package Service.Timesheet;

import java.util.List;

import javax.ejb.Local;

import entity.Project;
import entity.Ticket;


@Local
public interface ProjectServiceLocal {
	
	void createp(Project p);
	List<Project> findAll();
	Project getById(int id);
	void updatep(Project p);
	void deletep(int id);

}
