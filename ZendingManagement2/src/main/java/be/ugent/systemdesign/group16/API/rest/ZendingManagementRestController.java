package be.ugent.systemdesign.group16.API.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;
import be.ugent.systemdesign.group16.application.ZendingService;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Zending;

@RestController
@RequestMapping(path="/api/zendingen/")
@CrossOrigin(origins="*")
public class ZendingManagementRestController {
	
	@Autowired
	ZendingService zendingService;
	
	@PostMapping("/{id}")
	public ResponseEntity<String> aankomstNieuweZendingComplete(@PathVariable("id") Integer id, Adres afhaalpunt) {
		Response response = zendingService.bevestigAankomstNieuweZending(id, afhaalpunt);
		//according to REST specification, we should return the path of the newly created resource after a POST
		return createResponseEntity(response.status, "Aankomst nieuwe zending bevestigd, klant kan zending komen ophalen", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
	
	
}
