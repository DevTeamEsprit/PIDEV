package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Evaluation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private EvalType type;
	private Date date;
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	private Manager manager;
	
	public Evaluation(int id, EvalType type, Date date, Manager manager,boolean status) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.manager = manager;
		this.status = status;
	}
	
	@OneToMany(mappedBy = "evaluation",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Goal> goals = new ArrayList<Goal>();
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public int getId() {
		return id;
	}
	public void setId(int iD_Eval) {
		id = iD_Eval;
	}
	public EvalType getType() {
		return type;
	}
	public void setType(EvalType type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
	

}
