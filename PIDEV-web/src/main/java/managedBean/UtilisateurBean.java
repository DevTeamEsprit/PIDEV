package managedBean;

import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import entity.Commentaire;
import entity.Contrat;
import entity.Employe;
import entity.Publication;
import entity.TypeContrat;
import entity.Utilisateur;
import io.undertow.util.FileUtils;
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
	private Map<Publication, Commentaire> mapPublicationsselected = new HashMap<>();

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
	public void init() {
		this.listerEmployes();
		System.out.println(this.lstEmploye.size());
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
		System.out.println("sdvn");
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

	public Map<Publication, Commentaire> getMapPublicationsselected() {
		return mapPublicationsselected;
	}

	public void setMapPublicationsselected(Map<Publication, Commentaire> mapPublicationsselected) {
		this.mapPublicationsselected = mapPublicationsselected;
	}

	public String goProfile(Employe u) {
		this.selectedEmploye = u;
		this.mapPublicationsselected = this.serviceManager.getPubsUser(u);
		// System.out.println(u);
		return "Profile?faces-redirect=true";
	}

}
