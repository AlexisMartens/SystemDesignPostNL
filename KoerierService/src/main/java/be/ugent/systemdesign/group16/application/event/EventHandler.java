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
		Response response = koerierService.stuurKoerier(event.getOrderId(), event.getAfzender().getNaam(),
				event.getAfzender().getPostcode(), event.getAfzender().getStraat(), event.getAfzender().getPlaats(),
				event.getAfzender().getLand(), event.getOntvanger().getNaam(), event.getOntvanger().getPostcode(),
				event.getOntvanger().getStraat(), event.getOntvanger().getPlaats(), event.getOntvanger().getLand(),
				event.isSpoed(), event.isExtern(), event.getOrderStatus() == OrderStatus.OP_TE_HALEN ? true : false);

		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}

}
