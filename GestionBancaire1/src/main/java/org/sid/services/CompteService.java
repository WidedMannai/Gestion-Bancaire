package org.sid.services;

import org.sid.dao.CompteRepository;
import org.sid.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteService {

	
	@Autowired
	private CompteRepository repo;
	
	
	public void save(Compte compte) {
		repo.save(compte);
	}
}
