package org.sid.web;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.metier.BanqueMetierImpl;
import org.sid.metier.IBanqueMetier;
import org.sid.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BanqueController {
	
	
	@Autowired
	private BanqueMetierImpl banqueMetier;   
	
	@Autowired
	private CompteService service;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	
	/* @RequestMapping(value = "/save" , method = RequestMethod.POST)
	 public String saveCompte(@ModelAttribute("compte") Compte compte ) {
		 service.save(compte);
		 return"redirect:/client";
	 }*/
	
        
	@RequestMapping("/operation")
	public String index() {
		return "operations";
	}
	
	
	
	@RequestMapping("/client")
	public String index1(Model model) {
		model.addAttribute("client", new Client());
		//model.addAttribute("compte", new Compte());

		return "clients";
	}
	
	
	@RequestMapping("/opgenerale")
	public String index2(Model model ) {
        Page<Operation> pageOperations=banqueMetier.afficheOp(0, 5);
        for(Operation op :pageOperations.getContent()) {
        	System.err.println(op.getNumero());
        	System.err.println(op.getCompte().getCodeCompte());
        }
        System.err.println(pageOperations.getContent());
        model.addAttribute("operation",pageOperations.getContent());
        int[] pages=new int[pageOperations.getTotalPages()];
        model.addAttribute("pages",pages);
		return"opgenerale";
	}
	
	
	@PostMapping(path= "/saveNouveauCompte")
	public String saveNouveauCompte(Model model, Client client, @RequestParam(name="typeCpte", defaultValue="CC")String typeCpte,
			@RequestParam Long codeCompte) {
		clientRepository.save(client);
		//Compte compte;

		if(typeCpte.equals("CE")) {
		CompteEpargne	compteE = new CompteEpargne(null,new Date(),0,client,0);
		//codeCompte = compteE.getCodeCompte();

		compteRepository.save(compteE);
		//return "clients";
		return "redirect:/consulterCompte?codeCompte="+ compteE.getCodeCompte();

		}else {
		CompteCourant	compteC = new CompteCourant(null,new Date(),0,client,0);
		codeCompte = compteC.getCodeCompte();

		compteRepository.save(compteC);
		return "redirect:/consulterCompte?codeCompte="+ compteC.getCodeCompte();
		//return "clients";


		}
		/* try {
			 banqueMetier.saveNouveauCompte(cl);
		 }
		catch(Exception e) {
		       model.addAttribute("error",e);
		       return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage(); 
		       }*/
		
		}
	
	//5atya edhika mt3 lo5ra 
	 @GetMapping("/consulterCompte")
	 public String consulter(Model model , Long codeCompte , @RequestParam(name="page",defaultValue="0") int page ,@RequestParam(name="size",defaultValue="5") int size )  {
		 model.addAttribute("codeCompte", codeCompte);	
		 
		 try{
			 	Compte compte = banqueMetier.consulterCompte(codeCompte);
	            Page<Operation> pageOperations=banqueMetier.listOperation(codeCompte, page,size);
	            model.addAttribute("listOperations",pageOperations.getContent());
	            int[] pages=new int[pageOperations.getTotalPages()];
	            model.addAttribute("pages",pages);
	            model.addAttribute("compte",compte);
	            }
	            catch (Exception e){
	                model.addAttribute("exception",e);
	            }

	            return "operations";
	       
		 
	 }
	 
	 
	 
	
	 
	 @RequestMapping(value="/saveOperation" ,method = RequestMethod.POST )
	    public  String saveOperation(Model model ,  String typeOperation , Long codeCompte , double montant , Long codeCompte2){
	     model.addAttribute("codeCompte",codeCompte) ;
		 try{
	          if(typeOperation.equals("VERS")){
	              banqueMetier.verser(codeCompte,montant);
	          }       
	          else if(typeOperation.equals("RET")){
	              banqueMetier.retirer(codeCompte,montant);
	          }  if(typeOperation.equals("VIR")){
	              banqueMetier.virement(codeCompte,codeCompte2,montant);
	          }
	      }catch (Exception e){
	          model.addAttribute("error",e);
	          return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
	      }

	        return "redirect:/consulterCompte?codeCompte="+codeCompte;
	    }
	 
	 @RequestMapping(value="/activerDesactiver" ,method = RequestMethod.POST )
	 public String activerDesactiver(Model model , String etat, @RequestParam(value = "codeCompte")Long codeCompte ) {
		 
		System.err.println(codeCompte);
		 try {
			 if(etat.equals("ACTV")) {
				 banqueMetier.activerCompte(codeCompte);
			 }
			 if(etat.equals("DSCTV")) {
				 banqueMetier.desactiverCompte(codeCompte);
			 }}
	 catch(Exception e) {
	       model.addAttribute("error",e);
	       return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage(); 
	       }
	    
		 return "redirect:/consulterCompte?codeCompte="+codeCompte;
		 
		 
	 }
	 
	 
	 @GetMapping("/consulterClient")
	 public String consulterClient(Model model , Long codeClient )  {
		 model.addAttribute("codeClient", codeClient);		
		 try{
			 	Client client = banqueMetier.consulterClient(codeClient);
	            
	           
	            model.addAttribute("client",client);
	            }
	            catch (Exception e){
	                model.addAttribute("exception",e);
	            }

	            return "clients";
	 }
	 
	 
	
/*	@RequestMapping("/client")
	public String showNewCompte(Model model) {
		Compte compte=new Compte();
		model.addAttribute("compte",compte);
		return"clients";
	}

	 
	 
	 @RequestMapping(value = "/save" , method = RequestMethod.POST)
	 public String saveCompte(@ModelAttribute("compte") Compte compte ) {
		 banqueMetier.save(compte);
		 return"redirect:/operations";
	 }*/
}
