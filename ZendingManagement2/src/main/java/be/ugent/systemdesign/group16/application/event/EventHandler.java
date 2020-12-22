package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ZendingService;
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
		log.info("-Aangekomen zending");
		Response response = service.bevestigAankomstNieuweZending(new Zending(event.getTypeBestelling(), null, null, null, null, null,
				event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(), event.getPlaatsOntvanger(), event.getLandOntvanger(),
				event.getNaamAfzender(), event.getPostcodeAfzender(), event.getStraatAfzender(), event.getPlaatsAfzender(), event.getLandAfzender(),
				event.isOphalen(), event.isSpoed()));
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	
}