package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;

public interface EventDispatcher {

	void publishNieuweTrackAndTraceEvent(NieuweTrackAndTraceDomainEvent event);
}
