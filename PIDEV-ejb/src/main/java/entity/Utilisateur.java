package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

 

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id ;
	private String nom;
	private String prenom;
	private String cin;
	private String adresse ; 
	private String tel;
	private String email;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date datNais;
	private boolean Actif;
	private String image;
	
	@OneToOne
	private Contrat contrat;
	
	public Utilisateur(long id, String nom, String prenom, String cin, String adresse, String tel, String email,
			String password, Date datNais) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.datNais = datNais;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public boolean isActif() {
		return Actif;
	}



	public void setActif(boolean actif) {
		Actif = actif;
	}



	public Utilisateur() {
		super();
	}



	public Utilisateur(String nom, String prenom, String cin, String adresse, String tel, String email, String password,
			Date datNais) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.datNais = datNais;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getCin() {
		return cin;
	}



	public void setCin(String cin) {
		this.cin = cin;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Date getDatNais() {
		return datNais;
	}



	public void setDatNais(Date datNais) {
		this.datNais = datNais;
	}



	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", adresse=" + adresse
				+ ", tel=" + tel + ", email=" + email + ", password=" + password + ", datNais=" + datNais + "]";
	}
	
	
	
	
	
	
	
	
}
