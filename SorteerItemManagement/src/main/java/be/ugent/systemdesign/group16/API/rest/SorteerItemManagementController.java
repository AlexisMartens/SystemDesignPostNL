package be.ugent.systemdesign.group16.API.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;
import be.ugent.systemdesign.group16.application.SorteerItemService;
import be.ugent.systemdesign.group16.domain.SorteerItem;

@RestController
@RequestMapping(path ="/api/sorteeritem")
@CrossOrigin(origins = "*")
public class SorteerItemManagementController {

	@Autowired
	SorteerItemService service;
	
	@PostMapping("/brief")
	public ResponseEntity<String> maakBriefSorteerItem(@RequestBody SorteerItem _s){
		_s.setAanmaakDatum(LocalDate.now());
		Response r = service.maakNieuwSorteerItem(_s);
		return createResponseEntity(r.getStatus(),HttpStatus.OK,"SorteerItem correct aangemaakt",HttpStatus.CONFLICT,r.getMessage());
	}
	
	private static ResponseEntity<String> createResponseEntity(ResponseStatus status, HttpStatus happyStatus, String happyMessage, 
			HttpStatus sadStatus, String sadMessage){
		if(status==ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);
		return new ResponseEntity<>(happyMessage, happyStatus);
	}
}
