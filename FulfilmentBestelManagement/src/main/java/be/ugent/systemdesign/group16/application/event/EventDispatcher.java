package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.application.command.GetKlantenDataCommand;
import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;
import be.ugent.systemdesign.group16.domain.PacketDomainEvent;

public interface EventDispatcher {

	void publishNieuweTrackAndTraceEvent(NieuweTrackAndTraceDomainEvent event);
	
	//void sendGetKlantenDataCommand(GetKlantenDataDomainEvent event);
	void sendGetKlantenDataCommand(GetKlantenDataCommand command);

	void publishPacketDomainEvent(PacketDomainEvent event);

}
