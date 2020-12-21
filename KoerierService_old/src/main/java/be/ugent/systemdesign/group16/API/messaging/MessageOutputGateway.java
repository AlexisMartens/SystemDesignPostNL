package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.ExterneBestellingDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.UPDATE_TRACKANDTRACE_EVENT)
	void publishNieuweTrackAndTraceEvent(NieuweTrackAndTraceDomainEvent event);
	
	@Gateway(requestChannel = Channels.UPDATE_ZENDING_MANAGEMENT_EVENT)
	void publishExterneBestellingEvent(ExterneBestellingDomainEvent event);
}
