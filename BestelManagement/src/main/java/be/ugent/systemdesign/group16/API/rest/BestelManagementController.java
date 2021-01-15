package be.ugent.systemdesign.group16.API.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.group16.application.BestelService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;
import be.ugent.systemdesign.group16.domain.Bestelling;

@RestController
@RequestMapping(path="/api/bestel")
@CrossOrigin(origins="*")
public class BestelManagementController {
	
	@Autowired
	BestelService bestelService;
	
	@PostMapping("/")
	public ResponseEntity<String> maakBestelling(@RequestBody Bestelling b) {
		Response response = bestelService.plaatsBestelling(b);
		return createResponseEntity(response.status, "Bestelling registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	@PostMapping("/retour/{id}")
	public ResponseEntity<String> maakRetour(@PathVariable("id") Integer id) {
		Response response = bestelService.plaatsRetour(id);
		return createResponseEntity(response.status, "Bestelling registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
	
	
}
