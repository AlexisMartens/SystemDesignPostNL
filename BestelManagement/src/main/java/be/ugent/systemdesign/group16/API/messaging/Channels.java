package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

	static final String NIEUWE_TRACKANDTRACE_EVENT = "nieuwe_track_and_trace_event";
	static final String EXTERNE_BESTELLING_EVENT = "externe_bestelling_event";
	
	@Output(NIEUWE_TRACKANDTRACE_EVENT)
	MessageChannel NieuweTrackAndTraceEvent();
	
	@Output(EXTERNE_BESTELLING_EVENT)
	MessageChannel ExterneBestellingEvent();
	
}
