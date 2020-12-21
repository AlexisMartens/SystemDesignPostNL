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
@RequestMapping(path="api/koerier")
@CrossOrigin(origins="*")
public class KoerierServiceController {
	
	@Autowired
	Koerier koerierService;
	
	@PostMapping("/")
	public ResponseEntity<String> noteIntakeComplete(@RequestBody Bestelling b) {
		Response response = koerierService.bevestigAfleverenBuren(b);
		//according to REST specification, we should return the path of the newly created resource after a POST
		return createResponseEntity(response.status, "Intake registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	@PostMapping("retour/{id}")
	public ResponseEntity<String> noteIntakeComplete(@PathVariable("id") Integer id) {
		Response response = koerierService.bevestigOphalen(id);
		//according to REST specification, we should return the path of the newly created resource after a POST
		return createResponseEntity(response.status, "Intake registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	@PostMapping("retour/{id}")
	public ResponseEntity<String> noteIntakeComplete(@PathVariable("id") Integer id) {
		Response response = koerierService.bevestigAfhalen(id);
		//according to REST specification, we should return the path of the newly created resource after a POST
		return createResponseEntity(response.status, "Intake registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
	
	
}
