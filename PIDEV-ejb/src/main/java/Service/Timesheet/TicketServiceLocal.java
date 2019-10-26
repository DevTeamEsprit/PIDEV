package Service.Timesheet;

import java.util.List;

import javax.ejb.Local;

import entity.Ticket;


@Local
public interface TicketServiceLocal {
	
	void create(Ticket t);
	List<Ticket> findAll();
	void update(Ticket t);
	void delete(int id);

}
