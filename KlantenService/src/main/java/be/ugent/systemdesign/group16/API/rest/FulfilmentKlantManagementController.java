package be.ugent.systemdesign.group16.API.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.group16.application.FulfilmentKlantService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;
import be.ugent.systemdesign.group16.domain.FulfilmentKlant;

@RestController
@RequestMapping(path="/api/fulfilmentklant")
@CrossOrigin(origins="*")
public class FulfilmentKlantManagementController {
	
	@Autowired
	FulfilmentKlantService fulFilmentBestelService;
	
	@PostMapping("/")
	public ResponseEntity<String> maakFulfilmentKlant(@RequestBody FulfilmentKlant k) {
		Response response = fulFilmentBestelService.maakFulfilmentKlant(k);
		//according to REST specification, we should return the path of the newly created resource after a POST
		return createResponseEntity(response.status, "Nieuwe Fulfilmentklant gemaakt in FulfilmentBestelManagement with "+response.message, HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> stopFulfilmentKlant(@PathVariable("id") Integer id) {
		Response response = fulFilmentBestelService.stopFulfilmentKlant(id);
		//according to REST specification, we should return the path of the newly created resource after a POST
		return createResponseEntity(response.status, "Fulfilmentklant verwijderd uit FulfilmentBestelManagement with "+response.message, HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
	
	
}
