package org.sid.metier;

import java.util.Date;
import java.util.Optional;

import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;


public interface IBanqueMetier {

	public Compte consulterCompte(Long codeCompte);
	public void verser(Long codeCompte , double montant);
	public void retirer(Long codeCompte , double montant);
	public void virement(Long codeCompte1 , Long codeCompte2 , double montant);
	public Page<Operation> listOperation(Long codeCompte, int page , int size);
	public void activerCompte(Long codeCompte);
	public void desactiverCompte(Long codeCompte);
	public Client consulterClient( Long codeClient);
	/*public void save(Compte compte);*/
	public void saveNouveauCompte(Client cl,Compte cp);
	Optional<Compte> findById(Long codeCompte);
	public Page<Operation> afficheOp(int page , int size);

	
	
	
	
	
	
	
}
