package be.ugent.systemdesign.group16.application.event;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.MagazijnService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.domain.Adres;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	/*private Integer bestellingId;
	
	private String typeBestelling;
	
	private String naamAfzender;
	private String postcodeAfzender;
	private String straatAfzender;
	private String plaatsAfzender;
	private String landAfzender;
	
	private String naamOntvanger;
	private String postcodeOntvanger;
	private String straatOntvanger;
	private String plaatsOntvanger;
	private String landOntvanger;
	
	private boolean ophalen;
	
	private LocalDate aanmaakDatum;
	
	private boolean spoed;*/
	@Autowired
	MagazijnService service;
	//TODO: nog aanpassen
	public void handleNieuwPakket(PacketDomainEvent event) {
		log.info("-Received PacketDomainEvent van FulfilmentBestelManagement");
		Response response = service.MaakPakket(event.getBestellingId(), 
				event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(), event.getPlaatsOntvanger(), event.getLandOntvanger(),
				event.getNaamAfzender(), event.getPostcodeAfzender(), event.getStraatAfzender(), event.getPlaatsAfzender(), event.getLandAfzender(),
				event.getTypeBestelling(), event.isSpoed());
	}

	
	
	/*public void handleNieuweZending(ZendingDomainEvent event) {
		log.info("-Received NieuwZendingDomainEvent van BestelManagement");		
		Response response = service.maakNieuweZending(event.getBestellingId(), event.getTypeBestelling(), 
				event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
						event.getPlaatsOntvanger(), event.getLandOntvanger(),
				event.getNaamAfzender(), event.getPostcodeAfzender(), event.getStraatAfzender(),
						event.getPlaatsAfzender(), event.getLandAfzender(),event.isOphalen(), event.isSpoed());
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}*/
}