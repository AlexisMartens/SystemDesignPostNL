package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String NIEUWE_TRACKANDTRACE_EVENT = "nieuwe_track_and_trace_event";
	static final String GET_KLANTEN_DATA_REQUEST = "get_klanten_data_request";
	static final String GET_KLANTEN_DATA_RESPONSE = "get_klanten_data_response";
	static final String PACKET_EVENT = "packet_event";
	
	@Output(NIEUWE_TRACKANDTRACE_EVENT)
	MessageChannel NieuweTrackAndTraceEvent();
	
	@Output(GET_KLANTEN_DATA_REQUEST)
	MessageChannel GetKlantenDataRequest();
	
	@Output(PACKET_EVENT)
	MessageChannel PacketEvent();

	@Input(GET_KLANTEN_DATA_RESPONSE)
	SubscribableChannel GetKlantenDataResponse();
	
}
