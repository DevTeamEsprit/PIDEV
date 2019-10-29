package Service.Timesheet;

import java.util.List;

import javax.ejb.Local;

import entity.StatusTicket;
import entity.Ticket;



@Local
public interface TicketServiceLocal {
	
	void create(Ticket t);
	Ticket getById(int id);
	void update(Ticket t);
	void delete(int id);
	void addTicketToProject(int idTicket, int idProject);
	void assignTicketToEmploye(int idTicket, int idEmploye);
	void updateStatus(int idTicket,StatusTicket status);
	List<Ticket> findAll();
	List<Ticket> getTicketsByProject(int idProject);
	List<Ticket> getTicketsByProjectByStaus(int idProject,StatusTicket status);
	List<Ticket> getTicketsByEmployee(int idEmployee);
	List<Ticket> getTicketsByEmployeeByStaus(int idEmployee, StatusTicket status);


}

