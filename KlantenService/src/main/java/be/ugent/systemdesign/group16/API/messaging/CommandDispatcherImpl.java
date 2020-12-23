package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.command.CommandDispatcher;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataCommand;

@Component
public class CommandDispatcherImpl implements CommandDispatcher {
	
	@Autowired
	MessageOutputGateway outputGateway;
	
	@Value("${spring.cloud.stream.bindings." + Channels.GET_KLANTEN_DATA_RESPONSE + ".destination}")
	String assignRoomResponseDestination;

	@Override
	public void sendGetKlantenDataCommand(GetKlantenDataCommand command) {
		outputGateway.sendGetKlantenDataCommand(
			new GetKlantenDataCommand(command.getKlantId(), assignRoomResponseDestination)
		);
	}

}
