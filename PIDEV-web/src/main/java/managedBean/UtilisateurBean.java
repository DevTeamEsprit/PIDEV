package managedBean;

 
import java.io.IOException;

import java.io.Serializable;
 
import java.util.ArrayList;
import java.util.Base64;
 
 
import java.util.List;
 

import javax.annotation.PostConstruct;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
 
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import dto.PublicationCommentaireDto;
import entity.Commentaire;
import entity.Contrat;
import entity.Employe;
import entity.Publication;
 
 
import service.ServiceManager;

@ManagedBean(name = "utilisateurbean")
@SessionScoped
public class UtilisateurBean implements Serializable {

	@Inject
	private ServiceManager serviceManager;
	private Contrat contrat = new Contrat();
	private Employe emp = new Employe();
	private List<Employe> lstEmploye;
	private String dated, datef;
	 
 
	private Employe selectedEmploye;
 
	
	@ManagedProperty(value = "#{loginbean}")
	private Loginbean lb;
	
	public Loginbean getLb() {
		return lb;
	}

	public void setLb(Loginbean lb) {
		this.lb = lb;
	}

	public Employe getSelectedEmploye() {
		return selectedEmploye;
	}

	public void setSelectedEmploye(Employe selectedEmploye) {
		this.selectedEmploye = selectedEmploye;
	}

	public List<Employe> getLstEmploye() {
		return lstEmploye;
	}

	public void setLstEmploye(List<Employe> lstEmploye) {
		this.lstEmploye = lstEmploye;
	}

	@PostConstruct
	public void init() throws IOException {
		if (this.lb.getUser() == null) {
			this.serviceManager.goToPage("../login.jsf");
		}
		
		this.listerEmployes();
		
	}

	public String getDated() {
		return dated;
	}

	public void setDated(String dated) {
		this.dated = dated;
	}

	public String getDatef() {
		return datef;
	}

	public void setDatef(String datef) {
		this.datef = datef;
	}

	private String salaire;

	public String getSalaire() {
		return salaire;
	}

	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Contrat getContrat() {
	 
		return contrat;
	}

 
 
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Employe getEmp() {
		return emp;
	}

	public void setEmp(Employe emp) {
		this.emp = emp;
	}

	public void listerEmployes() {
		this.lstEmploye = this.serviceManager.listerEmploye();
	}

	public void ajouterEmploye() {

	}

	public void upload() {
		if (file != null) {

			byte[] fileContent = file.getContents();
			String encodedString = Base64.getEncoder().encodeToString(fileContent);

			emp.setContrat(contrat);
			this.emp.setImage(encodedString);

			this.serviceManager.addUser(emp);
			emp = new Employe();
			contrat = new Contrat();
			this.listerEmployes();
		} else {

		}

	}
	public List<PublicationCommentaireDto> convertListBeforeJava8(List<Publication> l) {
		List<PublicationCommentaireDto> list = new ArrayList<>();
		for (Publication pub : l) {
			list.add(new PublicationCommentaireDto(pub, new Commentaire(pub)));
		}
		return list;
	}


 

	public String goProfile(Employe u) {
		this.selectedEmploye = u;
		 
 	
		return "Profile?faces-redirect=true";
	}
	
 

}
