package Service.Timesheet;

import java.util.List;

import javax.ejb.Remote;

import entity.Ticket;

@Remote
public interface TicketServiceRemote {

	void create(Ticket t);
	List<Ticket> findAll();
	void update(Ticket t);
	void delete(int id);

}
