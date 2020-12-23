package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.command.GetKlantenDataCommand;
import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;
import be.ugent.systemdesign.group16.domain.PacketDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.GET_KLANTEN_DATA_RESPONSE)
	void sendGetKlantenDataCommand(GetKlantenDataCommand command);
	
	@Gateway(requestChannel = Channels.GET_KLANTEN_DATA_RESPONSE)
	void publishZendingEvent(PacketDomainEvent event);
}
