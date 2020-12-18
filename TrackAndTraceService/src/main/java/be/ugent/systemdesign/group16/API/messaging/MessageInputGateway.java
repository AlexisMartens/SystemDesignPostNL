package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.NieuweTrackAndTraceDomainEvent;
import be.ugent.systemdesign.group16.application.event.UpdateTrackAndTraceEvent;

@Component
public class MessageInputGateway {
	
	@Autowired
	EventHandler eventHandler;
	
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.NIEUWE_TRACKANDTRACE_EVENT)
	public void consumeNieuweTrackAndTraceEvent(NieuweTrackAndTraceDomainEvent event) {
		eventHandler.handleNewTrackAndTrace(event);
	}
	
	@StreamListener(Channels.UPDATE_TRACK_AND_TRACE_EVENT)
	public void consumeUpdateTrackAndTraceEvent(UpdateTrackAndTraceEvent event) {
		eventHandler.handleUpdateTrackAndTrace(event);
	}
}
