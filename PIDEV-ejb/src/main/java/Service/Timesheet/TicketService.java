package Service.Timesheet;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Employe;
import entity.Project;
import entity.StatusTicket;
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
	public Employe findemployeebyId(int id) {		
		return em.find(Employe.class, (long) id);
	}
	
	@Override
	public void delete(int id) {		
		Ticket t = em.find(Ticket.class, id);
		em.remove(t);		
	}

	@Override
	public Ticket getById(int id) {
		return em.find(Ticket.class, id);
	}
	
	@Override
	public void addTicketToProject(int idTicket, int idProject) {
		Project projet = em.find(Project.class, idProject);
		Ticket t = getById(idTicket);
		t.setProject(projet);
		update(t);
	}
	
	@Override
	public void assignTicketToEmploye(int idTicket, int idEmploye) {
		Long id = (long) idEmploye;
		Employe employee=em.find(Employe.class, id);
		Ticket t = getById(idTicket);
		t.setEmploye(employee);
		update(t);
		
	}

	@Override
	public void updateStatus(int idTicket, StatusTicket status) {
		// TODO Auto-generated method stub
		Ticket t = getById(idTicket);
		t.setStatus(status);
		update(t);
		
	}

	@Override
	public List<Ticket> getTicketsByProject(int idProject) {
		// TODO Auto-generated method stub
		return em.createQuery("from Ticket where project.id="+idProject, Ticket.class).getResultList();
		
	}

	@Override
	public List<Ticket> getTicketsByProjectByStaus(int idProject, StatusTicket status) {
	
		return em.createQuery("from Ticket t where t.project.id="+idProject+" and t.status='"+status.toString()+"'", Ticket.class).getResultList();
	}
	@Override
	public List<Ticket> getTicketsByEmployee(int idEmployee) {
		
		return em.createQuery("from Ticket where employe.id="+idEmployee, Ticket.class).getResultList();
	}
	@Override
	public List<Ticket> getTicketsByEmployeeByStaus(int idEmployee, StatusTicket status) {
		return em.createQuery("from Ticket t where t.employe.id="+idEmployee+" and t.status='"+status.toString()+"'", Ticket.class).getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		return em.createQuery("from Employe", Employe.class).getResultList();

	}
	
	
	


	


}
