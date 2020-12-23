package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.GetKlantenDataDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;
import be.ugent.systemdesign.group16.domain.PacketDomainEvent;

public interface EventDispatcher {

	void publishNieuweTrackAndTraceEvent(NieuweTrackAndTraceDomainEvent event);
	
	//void publishExterneBestellingEvent(GetKlantenDataDomainEvent event);
	
	void publishPacketEvent(PacketDomainEvent event);

}
