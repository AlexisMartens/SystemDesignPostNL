package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.ExterneBestellingDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;
import be.ugent.systemdesign.group16.domain.ZendingDomainEvent;

public interface EventDispatcher {

	void publishNieuweTrackAndTraceEvent(NieuweTrackAndTraceDomainEvent event);
	
	void publishExterneBestellingEvent(ExterneBestellingDomainEvent event);
	
	void publishZendingEvent(ZendingDomainEvent event);

}
