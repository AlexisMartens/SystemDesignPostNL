package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.BevestigSorterenItemDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher{
	
	@Gateway(requestChannel = Channels.BEVESTIG_SORTEREN_ITEM)
	void publishBevestigSorterenItemDomainEvent(BevestigSorterenItemDomainEvent e);
}
