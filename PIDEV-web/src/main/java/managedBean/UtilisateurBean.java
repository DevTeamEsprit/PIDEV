package managedBean;

 
import java.io.File;
import java.io.IOException;
 
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
 
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import entity.Contrat;
import entity.Employe;
import entity.TypeContrat;
import io.undertow.util.FileUtils;
import service.ServiceManager;

@ManagedBean(name="utilisateurbean")
@SessionScoped
public class UtilisateurBean implements Serializable{

	@Inject
	private ServiceManager serviceManager;
	private Contrat contrat = new Contrat();
	private Employe emp = new Employe();
	
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




	public void ajouterEmploye() {
		 
	}
	
	 
	 public void upload()  {
	        if(file != null) {
	         
	            
 
	            byte[] fileContent = file.getContents();
	            String encodedString = Base64.getEncoder().encodeToString(fileContent);
	          
	            emp.setContrat(contrat); 
	            this.emp.setImage(encodedString);
	         
	             this.serviceManager.addUser(emp);
	             emp=new Employe();
	             contrat=new Contrat();
	        }else {
	        	System.out.println("ta7chee");
            	 
	    }
	        
	 
	 }
	
}
