package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.application.event.UpdateTrackAndTraceEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {

	@Gateway(requestChannel = Channels.UPDATE_TRACK_AND_TRACE_EVENT)
	void publishUpdateTrackAndTraceEvent(UpdateTrackAndTraceEvent event);
	
}
