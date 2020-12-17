package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

	static final String NIEUWE_TRACKANDTRACE_EVENT = "nieuwe_track_and_trace_event";
	
	@Output(NIEUWE_TRACKANDTRACE_EVENT)
	MessageChannel NieuweTrackAndTraceEvent();
	
}
