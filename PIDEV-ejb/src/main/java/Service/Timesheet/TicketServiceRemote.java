package Service.Timesheet;

import java.util.List;

import javax.ejb.Remote;

import entity.StatusTicket;
import entity.Ticket;

@Remote
public interface TicketServiceRemote {

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


}
