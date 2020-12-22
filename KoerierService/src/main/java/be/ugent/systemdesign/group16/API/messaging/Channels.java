package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String UPDATE_TRACKANDTRACE_EVENT = "update_track_and_trace_event";
	static final String BEVESTIG_OPHALEN_ZENDING_EVENT = "bevestig_ophalen_zending_event";
	static final String BEVESTIG_AFLEVEREN_ZENDING_EVENT = "bevestig_afleveren_zending_event";
	static final String STUUR_KOERIER = "stuur_koerier";
	
	@Output(UPDATE_TRACKANDTRACE_EVENT)
	MessageChannel updateTrackAndTraceEvent();
	
	@Output(BEVESTIG_OPHALEN_ZENDING_EVENT)
	MessageChannel bevestigOphalenZendingEvent();
	
	@Output(BEVESTIG_AFLEVEREN_ZENDING_EVENT)
	MessageChannel bevestigAfleverenZendingEvent();
	
	@Input(STUUR_KOERIER)
	SubscribableChannel stuurKoerier();
}
