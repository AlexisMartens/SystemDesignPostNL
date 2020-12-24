package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.KlaarVoorKoerierDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuwSorteerItemDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.KLAAR_VOOR_KOERIER_EVENT)
	void publishKlaarVoorKoerierEvent(KlaarVoorKoerierDomainEvent event);
	
	@Gateway(requestChannel = Channels.NIEUW_SORTEER_ITEM)
	void publishNieuwSorteerItemEvent(NieuwSorteerItemDomainEvent event);

}
