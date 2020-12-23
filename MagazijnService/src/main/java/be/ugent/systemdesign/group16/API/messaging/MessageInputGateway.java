package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.Gateway;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.NieuwPakketDomainEventMOETIKKOPIERENVANFULLFILMENTMGMT;

@Component
public class MessageInputGateway {
	@Autowired
	EventHandler eventHandler;
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.NIEUW_PAKKET_EVENT)
	public void consumeNieuwPakketEvent(NieuwPakketDomainEventMOETIKKOPIERENVANFULLFILMENTMGMT event) {
		eventHandler.handleNieuwPakket(event);
	}
}
