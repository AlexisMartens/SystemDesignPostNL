package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String EXTERNE_BESTELLING_EVENT = "externe_bestelling_event";
	
	@Input(EXTERNE_BESTELLING_EVENT)
	SubscribableChannel ExterneBestellingEvent();
	
}
