package org.sid.sec;



import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sid.entities.Client;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
	
	
	

	
	@GetMapping(value="/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value="/")
	public String home() {
		return "redirect:/operation";
	}
	
	@RequestMapping(value="/403")
	public String accessDenied() {
		return "403";         
	}
/*	
	UserRepository userRepository;
 // hedha li 5demtou f login mt3i 9bal class user
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	
	@RolesAllowed("USER")
	@RequestMapping(value="/")
	/*@RequestMapping(value="/register")*/
	/*public String home() {
	
		/*return "redirect:/operation"; //lahne n7ot condition ken l'attribut user 3edi si nn     */
		
	/*	return " redirect:/nouveauUser";
	}
	
	/*
	
	@PostMapping(path= "/register")
	public String saveNouveauUser(Model model, User user,
			@RequestParam Long id) {
		
		userRepository.save(user);

		
		return "redirect:/nouveauUser";
		


		}*/

	
	//asm3ni ne zeda m lowl golt
	
	
	
	
	
	/* wided   UserRepository userRepository;
	
	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping(path="/registration")
	public String saveUser(Model model,@Valid User user, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) return "registration";
		
		BCryptPasswordEncoder bcpe=new BCryptPasswordEncoder();
		String u2=user.getPassword();
		user.setPassword(bcpe.encode(u2));
		userRepository.save(user);
		model.addAttribute("user", user);
		return "redirect:/nouveauUser";
	}
	
	
	
	
	wided	*/
		
	
	
}

   
	

