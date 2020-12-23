package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.NieuwSorteerItemDomainEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;

public interface EventDispatcher {

	void publishUpdateTrackAndTraceEvent(UpdateTrackAndTraceDomainEvent event);
	
	void publishNieuwSorteerItemEvent(NieuwSorteerItemDomainEvent event);
}
