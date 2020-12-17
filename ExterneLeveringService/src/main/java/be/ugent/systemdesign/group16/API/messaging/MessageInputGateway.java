package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.ExterneBestellingDomainEvent;

@Component
public class MessageInputGateway {
	
	@Autowired
	EventHandler eventHandler;
	
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.EXTERNE_BESTELLING_EVENT)
	public void consumeExterneBestellingEvent(ExterneBestellingDomainEvent event) {
		eventHandler.handleExterneLevering(event);
	}
}
