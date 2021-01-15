package be.ugent.systemdesign.group16.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.group16.application.KoerierService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;

@RestController
@RequestMapping(path="/api/koerier/")
@CrossOrigin(origins="*")
public class KoerierRestController {

	@Autowired
	KoerierService koerierService;
	
	@PutMapping("{orderId}/afgeleverd")
	public ResponseEntity<String> bevestigAfleveren(@PathVariable("orderId") Integer orderId) {
		Response response = koerierService.bevestigAfleveren(orderId);
		return createResponseEntity(response.status, "Aflevering bevestigd", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	@PutMapping("{orderId}/afgeleverdBuren")
	public ResponseEntity<String> bevestigAfleverenBuren(@PathVariable("orderId") Integer orderId) {
		Response response = koerierService.bevestigAfleveren(orderId);
		return createResponseEntity(response.status, "Aflevering bij buren bevestigd", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	 
	@PutMapping("{orderId}/opgehaald")
	public ResponseEntity<String> bevestigOphalen(@PathVariable("orderId") Integer orderId) {
		Response response = koerierService.bevestigOphalen(orderId);
		return createResponseEntity(response.status, "Ophaling bevestigd", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
}
