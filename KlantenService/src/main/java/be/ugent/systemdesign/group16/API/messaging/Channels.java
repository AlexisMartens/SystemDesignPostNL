package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String GET_KLANTEN_DATA_REQUEST = "get_klanten_data_request";
	static final String GET_KLANTEN_DATA_RESPONSE = "get_klanten_data_response";
	
	@Output(GET_KLANTEN_DATA_RESPONSE)
	MessageChannel GetKlantenDataResponse();

	@Input(GET_KLANTEN_DATA_REQUEST)
	SubscribableChannel GetKlantenDataRequest();
	
}
