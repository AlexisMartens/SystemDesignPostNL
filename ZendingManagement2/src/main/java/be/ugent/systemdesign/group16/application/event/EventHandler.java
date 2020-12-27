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
		Response response = service.bevestigAankomstNieuweZending(event.getBestellingId(), new Adres(event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
					event.getPlaatsOntvanger(), event.getLandOntvanger()));
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	//TODO: nog aanpassen service methode! Maar hoe???
	public void handleNieuweZending(NieuweZendingDomainEvent event) {
		log.info("-Received NieuwZendingDomainEvent van SorteerItemManagement");
		Response response=null; 
		//TODO: operaties uitvoeren...
		
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());

	}
	public void handleBevestigOphalenZending(BevestigOphalenZendingEvent event) {
		log.info("-Received BevestigOphalenZendingEvent van KoerierService");
		Response response = service.bevestigOphalenZending(event.getOrderId());	
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	public void handleBevestigAfleverenZending(BevestigAfleverenZendingEvent event) {
		log.info("-Received BevestigAfleverenZendingEvent van KoerierService");
		Response response = service.bevestigAfleverenZending(event.getOrderId(), 
				new Adres(event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
						event.getPlaatsOntvanger(), event.getLandOntvanger()));				
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}	
}