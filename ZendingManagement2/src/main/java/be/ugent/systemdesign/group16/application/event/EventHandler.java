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
	//bevestigOphalenZending
	//bevestigAfleverenZending
	//nieuwezending sorteeritemmgmt
	public void handleNieuweZending(ZendingDomainEvent event) {
		log.info("-Received NieuwZendingDomainEvent van BestelManagement");
		Response response; 

		// ophalen bij klant thuis
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
	
	public void handleNieuwSorteerItemEvent(NieuwSorteerItemEvent e) {
		log.info("Received NieuwSorteerItemEvent.");
		service.maakNieuwSorteerItem(e.getTrackId(), e.getNaamOntvanger(), e.getPostcodeOntvanger(), e.getStraatOntvanger(), 
				e.getPlaatsOntvanger(), e.getLandOntvanger(), e.getNaamAfzender(), e.getPostcodeAfzender(), 
				e.getStraatAfzender(), e.getPlaatsAfzender(), e.getLandAfzender(), e.getNaamHuidigeLocatie(), 
				e.getPostcodeHuidigeLocatie(), e.getStraatHuidigeLocatie(), e.getPlaatsHuidigeLocatie(), 
				e.getLandHuidigeLocatie(), e.getTypeZending(), e.isSpoed(), e.getAanmaakDatum());
	}
	
	public void handleBevestigSorterenEvent(BevestigSorterenEvent e) {
		log.info("Received BevestigSorterenEvent.");
		service.gesorteerd(e.getSorteerItemId(), e.getNaamVolgendeLocatie(), e.getPostcodeVolgendeLocatie(), e.getStraatVolgendeLocatie(), 
				e.getPlaatsVolgendeLocatie(), e.getLandVolgendeLocatie(), e.isLaatsteLocatie(), e.getBatchId());
	}
	
	public void handleBevestigVervoerenEvent(BevestigVervoerenEvent e) {
		log.info("Received BevestigVervoerenEvent.");
		service.vervoerd(e.getSorteerItemId(), e.getNaamNieuweLocatie(), e.getPostcodeNieuweLocatie(), e.getStraatNieuweLocatie(), 
				e.getPlaatsNieuweLocatie(), e.getLandNieuweLocatie());
	}
	
}