package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.KoerierService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.domain.OrderStatus;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

	@Autowired
	KoerierService koerierService;
	
	public void handleStuurKoerier(StuurKoerierDomainEvent event) {
		Response response = koerierService.stuurKoerier(event.getZendingId(), event.getTypeZending(), event.getNaamAfzender(),
				event.getPostcodeAfzender(), event.getStraatAfzender(), event.getPlaatsAfzender(), event.getLandAfzender(), 
				event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(), event.getPlaatsOntvanger(),
				event.getLandOntvanger(), event.getNaamHuidigeLocatie(), event.getPostcodeHuidigeLocatie(), event.getStraatHuidigeLocatie(),
				event.getPlaatsHuidigeLocatie(), event.getLandHuidigeLocatie(),	event.isSpoed());

		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}

}
