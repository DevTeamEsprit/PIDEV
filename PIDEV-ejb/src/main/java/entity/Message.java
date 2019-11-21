package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Message implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMessage;

	@Column(columnDefinition = "MEDIUMTEXT")
	private String content;

	@ManyToOne
	@JoinColumn(name = "id_sender")
	//@JsonManagedReference
	private Utilisateur sender;

	@ManyToOne
	@JoinColumn(name = "id_receiver")
	//@JsonManagedReference
	private Utilisateur receiver;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Message() {
		super();
	}

	public Message(String content, Utilisateur sender, Utilisateur receiver, Date date) {
		super();
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
	}

	public Message(Long idMessage, String content, Utilisateur sender, Utilisateur receiver, Date date) {
		super();
		this.idMessage = idMessage;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Utilisateur getSender() {
		return sender;
	}

	public void setSender(Utilisateur sender) {
		this.sender = sender;
	}

	public Utilisateur getReceiver() {
		return receiver;
	}

	public void setReceiver(Utilisateur receiver) {
		this.receiver = receiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
