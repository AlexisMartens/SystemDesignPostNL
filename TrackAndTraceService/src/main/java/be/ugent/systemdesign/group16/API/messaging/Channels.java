package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String NIEUWE_TRACKANDTRACE_EVENT = "nieuwe_track_and_trace_event";
	
	@Input(NIEUWE_TRACKANDTRACE_EVENT)
	SubscribableChannel NieuweTrackAndTraceEvent();
	
}
