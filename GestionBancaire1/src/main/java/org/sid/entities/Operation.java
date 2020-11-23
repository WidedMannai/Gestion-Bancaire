package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity     
@Table(name="operation")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OP",
discriminatorType=DiscriminatorType.STRING,length=1)
public class Operation implements Serializable { //abstract car on va faire soit versement soit retrait
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numero ;
	
	private Date dateOperation; 
	private double montant ;
	
	
	@ManyToOne
	private Compte compte; // c'est une association
	
	
	public Operation() {
		
		// TODO Auto-generated constructor stub
	}


	public Operation(Date dateOperation, double montant, Compte compte) {
		
		this.dateOperation = dateOperation;
		this.montant = montant;
	    this.compte = compte;
	}


	public Long getNumero() {
		return numero;
	}


	public void setNumero(Long numero) {
		this.numero = numero;
	}


	public Date getDateOperation() {
		return dateOperation;
	}


	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public Compte getCompte() {
		return compte;
	}


	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}