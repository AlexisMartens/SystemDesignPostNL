package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.BevestigAfleverenZendingEvent;
import be.ugent.systemdesign.group16.domain.BevestigOphalenZendingEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.BEVESTIG_AFLEVEREN_ZENDING_EVENT)
	void publishNieuweTrackAndTraceEvent(BevestigAfleverenZendingEvent event);
	
	@Gateway(requestChannel = Channels.BEVESTIG_OPHALEN_ZENDING_EVENT)
	void publishExterneBestellingEvent(BevestigOphalenZendingEvent event);
	
	@Gateway(requestChannel = Channels.UPDATE_TRACKANDTRACE_EVENT)
	void publishZendingEvent(UpdateTrackAndTraceEvent event);
}
