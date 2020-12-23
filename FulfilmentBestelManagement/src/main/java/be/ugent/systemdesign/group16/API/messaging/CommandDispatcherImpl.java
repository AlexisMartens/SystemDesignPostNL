package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommandDispatcherImpl implements CommandDispatcher {
	
	@Autowired
	MessageOutputGateway outputGateway;
	
	@Value("${spring.cloud.stream.bindings." + Channels.ASSIGN_ROOM_RESPONSE + ".destination}")
	String assignRoomResponseDestination;

	@Override
	public void sendAssignRoomCommand(AssignRoomCommand command) {
		outputGateway.sendAssignRoomCommand(
			new AssignRoomCommand(command.getInpatientId(), assignRoomResponseDestination)
		);
	}

	@Override
	public void sendRegisterInpatientIntakeCommand(RegisterInpatientIntakeCommand command) {
		outputGateway.sendRegisterInpatientIntakeCommand(command);
	}

}
