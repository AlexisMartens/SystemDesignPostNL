package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.ExterneLeveringService;
import be.ugent.systemdesign.group16.application.Response;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	ExterneLeveringService service;
	
	public void handleExterneLevering(ExterneBestellingDomainEvent event) {
		log.info("-ExternerLevering");
		Response response = service.MaakBestelling(Integer.parseInt(event.getBestellingId()), event.getTypeBestelling(), event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(), event.getPlaatsOntvanger(), event.getLandOntvanger(), event.getNaamAfzender(), event.getPostcodeAfzender(), event.getStraatAfzender(), event.getPlaatsAfzender(), event.getLandAfzender(), event.isOphalen(), event.getStatus(), event.isSpoed(), event.isExtern(), event.getExterneLeveringService());
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
}
