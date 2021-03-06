package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.NieuwSorteerItemDomainEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	@Gateway(requestChannel = Channels.UPDATE_TRACK_AND_TRACE_EVENT)
	void publishUpdateTrackAndTraceEvent(UpdateTrackAndTraceDomainEvent event);
	
	@Gateway(requestChannel = Channels.NIEUW_SORTEERITEM_EVENT)
	void publishNieuwSorteerItemEvent(NieuwSorteerItemDomainEvent event);
}
