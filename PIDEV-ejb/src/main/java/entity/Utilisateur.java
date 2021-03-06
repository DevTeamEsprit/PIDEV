package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

 

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String prenom;
	private String cin;
	private String adresse;
	private String tel;
	private String email;
	@JsonIgnore
	private String password;
	@Temporal(TemporalType.DATE)
	@JsonIgnore
	private Date datNais;
	@JsonIgnore
	private boolean Actif;
	@Column(columnDefinition = "MEDIUMTEXT")
	//@JsonIgnore
	private String image;
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private Sexe sexe;
	@JsonIgnore
	private boolean firstLogin;

	
	public List<DemandeFormation> getLstdemande() {
		return lstdemande;
	}

	public void setLstdemande(List<DemandeFormation> lstdemande) {
		this.lstdemande = lstdemande;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	@OneToMany(mappedBy = "sender")
//	@JsonBackReference
	@JsonIgnore
	private List<Message> messagesSent;

	@OneToMany(mappedBy = "receiver")
	//@JsonBackReference
	@JsonIgnore
	private List<Message> messagesReceived;

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE })
	//@JsonBackReference
	@JsonIgnore
	private List<Publication> lstPub;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE })
	//@JsonManagedReference(value="user_com")
	@JsonIgnore
	private List<Commentaire> lstcom;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE })
	//@JsonBackReference
	@JsonIgnore
	private List<DemandeFormation> lstdemande;
 

	public List<Commentaire> getLstcom() {
		return lstcom;
	}

	public void setLstcom(List<Commentaire> lstcom) {
		this.lstcom = lstcom;
	}
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	//@JsonBackReference
	@JsonIgnore
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

	public List<Publication> getLstPub() {
		return lstPub;
	}

	public void setLstPub(List<Publication> lstPub) {
		this.lstPub = lstPub;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
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

	public List<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", adresse=" + adresse
				+ ", tel=" + tel + ", email=" + email + ", password=" + password + ", datNais=" + datNais + "]";
	}

 
 
 
 
	
	
	
}
 
