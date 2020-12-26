package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ZendingService;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Zending;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	ZendingService service;

	public void handleNieuweZending(ZendingDomainEvent event) {
		log.info("-Received NieuwZendingDomainEvent van BestelManagement");
		Response response; 

		// ophalen bij klant thuis
		//IF CHECKS MOET IN ZENDINGSERVICE ipv in eventhandler
		if(event.isOphalen()) {
			response = service.bevestigAankomstNieuweZending(event.getBestellingId(), new Adres(event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
					event.getPlaatsOntvanger(), event.getLandOntvanger()));
		}
		// zending moet naar afhaalpunt
		else {
			response = service.bevestigAankomstNieuweZending(event.getBestellingId(), new Adres(event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
					event.getPlaatsOntvanger(), event.getLandOntvanger()));
		}
				/*(new Zending(event.getTypeBestelling(), null, null, null, null, null,
				event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(), event.getPlaatsOntvanger(), event.getLandOntvanger(),
				event.getNaamAfzender(), event.getPostcodeAfzend*er(), event.getStraatAfzender(), event.getPlaatsAfzender(), event.getLandAfzender(),
				event.isOphalen(), event.isSpoed()))*/
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	public void handleNieuweZending(NieuweZendingDomainEvent event) {
		log.info("-Received NieuwZendingDomainEvent van SorteerItemManagement");
		Response response; 
		//TODO: operaties uitvoeren...
		
		
		
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());

	}
	public void handleBevestigOphalenZending(BevestigOphalenZendingEvent event) {
		log.info("-Received BevestigOphalenZendingEvent van KoerierService");
		Response response; 
		//TODO: nog checks??
		response = service.bevestigAfhalen(event.getOrderId());	
		
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	public void handleBevestigAfleverenZending(BevestigAfleverenZendingEvent event) {
		log.info("-Received BevestigAfleverenZendingEvent van KoerierService");
		Response response; 
		//TODO: nog checks?? Is ontvangeradres het afhaalpuntadres?
		response = service.bevestigAankomstNieuweZending(event.getOrderId(), 
				new Adres(event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
						event.getPlaatsOntvanger(), event.getLandOntvanger()));
				
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}	
}