package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.BevestigVervoerenItemDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher{

	@Gateway(requestChannel = Channels.BEVESTIG_VERVOEREN_ITEM)
	void publishBevestigVervoerenItemEvent(BevestigVervoerenItemDomainEvent e);
}
