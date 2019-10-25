package managedBean;

 
import java.io.File;
import java.io.IOException;
 
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
 
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import entity.Contrat;
import entity.Employe;
import io.undertow.util.FileUtils;
import service.ServiceManager;

@ManagedBean(name="utilisateurbean")
@SessionScoped
public class UtilisateurBean implements Serializable{

	@Inject
	private ServiceManager serviceManager;
	private Contrat contrat;
	private Employe emp = new Employe();
	

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
		System.out.println("lehnaaa nchala nemchii "+emp.toString());
		 
		 
		this.upload();
	}
	
	 
	 public void upload()  {
	        if(file != null) {
	            System.out.println("Succesful"+ file.getFileName() + " is uploaded.");
	            
	        
	            byte[] fileContent = file.getContents();
	            String encodedString = Base64.getEncoder().encodeToString(fileContent);
	             System.out.println(encodedString);
	             
	        //     this.emp.setImage(encodedString);
	             this.serviceManager.addUser(emp);
	        }else {
	        	System.out.println("ta7chee");
            	 
	    }
	        
	 
	 }
	
}
