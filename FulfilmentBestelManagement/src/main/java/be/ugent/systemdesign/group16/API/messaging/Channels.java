package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	public static final String NIEUWE_TRACKANDTRACE_EVENT = "nieuwe_track_and_trace_event";
	public static final String GET_KLANTEN_DATA_REQUEST = "get_klanten_data_request";
	public static final String GET_KLANTEN_DATA_RESPONSE = "get_klanten_data_response";
	public static final String PACKET_EVENT = "packet_event";
	
	@Output(NIEUWE_TRACKANDTRACE_EVENT)
	MessageChannel nieuweTrackAndTraceEvent();
	
	@Output(GET_KLANTEN_DATA_REQUEST)
	MessageChannel getKlantenDataRequest();
	
	@Output(PACKET_EVENT)
	MessageChannel packetEvent();

	@Input(GET_KLANTEN_DATA_RESPONSE)
	SubscribableChannel getKlantenDataResponse();
	
}
