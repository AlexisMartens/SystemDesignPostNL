package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

	static final String UPDATE_TRACKANDTRACE_EVENT = "update_track_and_trace_event";
	static final String UPDATE_ZENDING_MANAGEMENT_EVENT = "update_zending_management_event";
	
	@Output(UPDATE_TRACKANDTRACE_EVENT)
	MessageChannel UpdateTrackAndTraceEvent();
	
	@Output(UPDATE_ZENDING_MANAGEMENT_EVENT)
	MessageChannel ExterneBestellingEvent();
	
}
