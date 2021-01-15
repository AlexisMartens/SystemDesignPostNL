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
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.group16.application.MagazijnService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;

@RestController
@RequestMapping(path="/api/magazijn")
@CrossOrigin(origins="*")
public class MagazijnServiceController {
	
	@Autowired
	MagazijnService service;
	
	@PostMapping("/{id}/bevestigInpakken")
	public ResponseEntity<String> BevestigInpakkenComplete(@PathVariable("id") Integer id ) {
		Response response = service.BevestigInpakken(id);
		return createResponseEntity(response.status, "Pakket is uit het magazijn gehaald, ingepakt en afgeleverd aan het sorteercentrum", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
}