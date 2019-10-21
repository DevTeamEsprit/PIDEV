package Service.Timesheet;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Employe;
import entity.Ticket;


@Stateless
public class TicketService implements TicketServiceRemote, TicketServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    public TicketService() {
    }

	public void create(Ticket t) {
		em.persist(t);
	}

	public List<Ticket> findAll() {
		return em.createQuery("from Ticket", Ticket.class).getResultList();
	}
	
	@Override
	public void update(Ticket t) {
		em.merge(t);
	}
	
	
	@Override
	public void delete(int id) {
		
		Ticket t = em.find(Ticket.class, id);
		em.remove(t);
		
	}


}
