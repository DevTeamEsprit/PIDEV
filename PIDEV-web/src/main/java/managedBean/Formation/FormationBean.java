package managedBean.Formation;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import Service.Formation.DemandeFormationService;
import Service.Formation.FormationService;
import entity.DemandeFormation;
import entity.Formation;
import managedBean.Loginbean;
import service.ServiceManager;


@ManagedBean
@ViewScoped
public class FormationBean implements Serializable{
	
	@ManagedProperty(value = "#{loginbean}")
	private Loginbean lb;
	
	private List<Formation> lstFormation;
	private Formation f= new Formation();
	
	@EJB
	private FormationService formationService;
	@EJB
	private DemandeFormationService demandeFormationService;
	
	@Inject
	private ServiceManager serviceManager;
	
	@PostConstruct
	public void init() throws IOException {
		if (this.lb.getUser() == null) {
			this.serviceManager.goToPage("../login.jsf");
		}		 
		this.getFomrmations();
	}
	
	public void getFomrmations() {
		this.lstFormation=this.formationService.listerForamtion();
	}
	
	public void addFormation() {
		this.formationService.addFormation(f);
		this.getFomrmations();
		f=new Formation();
	}

	public Loginbean getLb() {
		return lb;
	}

	public void setLb(Loginbean lb) {
		this.lb = lb;
	}

	public List<Formation> getLstFormation() {
		return lstFormation;
	}

	public void setLstFormation(List<Formation> lstFormation) {
		this.lstFormation = lstFormation;
	}

	public Formation getF() {
		return f;
	}

	public void setF(Formation f) {
		this.f = f;
	}

	public FormationService getFormationService() {
		return formationService;
	}

	public void setFormationService(FormationService formationService) {
		this.formationService = formationService;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	public void participe(Formation f) {
		System.out.println(f);
		DemandeFormation d= new DemandeFormation();
		d.setFormation(f);
		d.setUser(lb.getUser());
		d.setDemande(new Date());
		this.demandeFormationService.addFormation(d);
		this.getFomrmations();
	}
	
}///////
