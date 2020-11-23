package org.sid.metier;

import java.util.Date;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.websocket.server.PathParam;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.Client;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{
    @Autowired
	private CompteRepository compteRepository;
    @Autowired
	private OperationRepository operationRepository;
    
    @Autowired
    private ClientRepository clientRepository ;
  /*  
    public void save(Compte compte) {
    	compteRepository.save(compte);
    }*/
    
	@Override
    public void saveNouveauCompte(Client cl, Compte cp) {
    	/* cl=new Client();
    	 cp=new Compte( null,new Date(), solde, cl);
  	  clientRepository.save(cl);
  	  
  	  compteRepository.save(cp);*/
    }

	@Override
   public Page<Operation> afficheOp( int page, int size) {
		return operationRepository.afficheOp(PageRequest.of(page,size));
	}	
	
    @Override
	public Compte consulterCompte( Long codeCompte) {
    	 Optional<Compte> compteOptional=compteRepository.findById(codeCompte);
			if(compteOptional.isPresent() ) {
				Compte cp=compteOptional.get();
				return cp;
			}
			else throw new RuntimeException("Compte introuvable ");
	}
	/* */
    @Override
    public Optional<Compte> findById(Long codeCompte) {

        return compteRepository.findById(codeCompte);
    }
    /* */
	@Override
	public void verser(Long codeCompte, double montant) {
     /* Compte cp=consulterCompte(codeCompte);
      Versement v=new Versement(new Date() ,montant ,cp);
	  operationRepository.save(v);
	  cp.setSolde(cp.getSolde()+montant);
	  compteRepository.save(cp);*/
		if(findById(codeCompte).isPresent()){
	        Optional<Compte> cp = findById(codeCompte);
	        Versement v = new Versement(new Date() , montant, cp.get());
	        operationRepository.save(v);
	        cp.get().setSolde(cp.get().getSolde()+montant);
	        compteRepository.save(cp.get());}
	}

	@Override
	public void retirer(Long codeCompte, double montant) {
		 Compte cp=consulterCompte(codeCompte);
	     double  facilitesCaisse=0;
		 if (cp instanceof CompteCourant)
			 facilitesCaisse=((CompteCourant) cp).getDecouvert();
		 if(cp.getSolde()+facilitesCaisse < montant)
			 throw new RuntimeException("solde insuffisant");
		 Retrait r=new Retrait(new Date() ,montant ,cp);
		  operationRepository.save(r);
		  cp.setSolde(cp.getSolde() - montant);
		  compteRepository.save(cp);		
	}

	@Override
	public void virement(Long codeCompte1, Long codeCompte2, double montant) {
       if (codeCompte1 == codeCompte2)
    	   throw new RuntimeException("Impossible d'effectuer un virement sur le meme compte");
		retirer(codeCompte1,montant);	
               verser(codeCompte2 , montant);
	}

	@Override
	public Page<Operation> listOperation(Long codeCompte, int page, int size) {
		return operationRepository.listOperation(codeCompte, PageRequest.of(page,size));
	}
	

	
	@Override
	public void activerCompte(Long codeCompte) {
		Compte cp=consulterCompte(codeCompte);
		String act="active";
		cp.setEtat(act);
		compteRepository.save(cp);
		
	}

	@Override
	public void desactiverCompte(Long codeCompte) {
		Compte cp=consulterCompte(codeCompte);
		if(cp.getSolde()==0)
		{String des="desactive";
		cp.setEtat(des);}
		//cp.setSolde(0);
		else throw new RuntimeException("Vous avez encore de solde dans votre compte ");
		compteRepository.save(cp);
	}




	 @Override
	public Client consulterClient( Long codeClient) {
    	 Optional<Client> clientOptional=clientRepository.findById(codeClient);
			if(clientOptional.isPresent() ) {
				Client cl=clientOptional.get();
				return cl;
			}
			else throw new RuntimeException("Client introuvable ");
		
	}

		
	
	
	
	

}
