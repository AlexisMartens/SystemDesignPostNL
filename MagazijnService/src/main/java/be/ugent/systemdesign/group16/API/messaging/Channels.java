package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
public interface Channels {

	static final String NIEUW_SORTEERITEM_EVENT = "nieuw_sorteeritem_event";
	static final String NIEUW_PAKKET_EVENT = "nieuw_pakket_event";
	static final String UPDATE_TRACK_AND_TRACE_EVENT = "update_track_and_trace_event";
	
	@Output(UPDATE_TRACK_AND_TRACE_EVENT)
	MessageChannel UpdateTrackAndTrace();
	
	@Output(NIEUW_SORTEERITEM_EVENT)
	MessageChannel NieuwSorteerItemEvent();
	
	@Input(NIEUW_PAKKET_EVENT)
	SubscribableChannel NieuwPakketEvent();
}
