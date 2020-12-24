package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.StuurVervoerderEvent;

@Component
public class MessageInputGateway {

	@Autowired
	EventHandler eventHandler;
	
	@StreamListener(Channels.STUUR_VERVOERDER)
	public void consumeStuurVervoerderEvent(StuurVervoerderEvent e) {
		eventHandler.handleStuurVervoerderEvent(e);
	}
}