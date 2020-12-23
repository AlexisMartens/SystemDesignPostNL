package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.command.CommandHandler;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataResponse;

@Component
public class MessageInputGateway {

	@Autowired
	CommandHandler commandHandler;
	
	@StreamListener(Channels.GET_KLANTEN_DATA_RESPONSE)
	public void consumeGetKlantenDataResponse(GetKlantenDataResponse response) {
		commandHandler.handleGetKlantenDataResponse(response);
	}
	
}
