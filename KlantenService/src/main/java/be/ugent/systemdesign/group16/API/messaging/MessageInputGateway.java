package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.command.CommandHandler;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataCommand;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataResponse;

@Component
public class MessageInputGateway {

	@Autowired
	CommandHandler commandHandler;
	
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.GET_KLANTEN_DATA_RESPONSE)
	public void receiveGetKlantenDataResponse(GetKlantenDataCommand command) {
		GetKlantenDataResponse response = commandHandler.getKlantenData(command);
		channels.getKlantenDataResponse().send(
				MessageBuilder
				.withPayload(response)
				.setHeader("spring.cloud.stream.sendto.destination", command.getResponseDestination())
				.build()
		);
	}

}
