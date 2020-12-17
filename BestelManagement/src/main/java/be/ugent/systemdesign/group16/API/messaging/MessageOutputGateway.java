package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.ExterneBestellingDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.NIEUWE_TRACKANDTRACE_EVENT)
	void publishNieuweTrackAndTraceEvent(NieuweTrackAndTraceDomainEvent event);
	
	@Gateway(requestChannel = Channels.EXTERNE_BESTELLING_EVENT)
	void publishExterneBestellingEvent(ExterneBestellingDomainEvent event);
}
