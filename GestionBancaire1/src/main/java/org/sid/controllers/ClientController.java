package org.sid.controllers;

import org.sid.dao.ClientRepository;
import org.sid.entities.Client;
import org.sid.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/client")
	public Object setClien(@RequestBody Client client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.saveClient(client));
	}

}//
