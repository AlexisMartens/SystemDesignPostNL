package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.NieuweZendingDomainEvent;
import be.ugent.systemdesign.group16.domain.StuurSorteerderDomainEvent;
import be.ugent.systemdesign.group16.domain.StuurVervoerderDomainEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;

public interface EventDispatcher {
	void publishNieuweZendingDomainEvent(NieuweZendingDomainEvent e);
	void publishStuurSorteerderDomainEvent(StuurSorteerderDomainEvent e);
	void publishStuurVervoerderDomainEvent(StuurVervoerderDomainEvent e);
	void publishUpdateTrackAndTraceDomainEvent(UpdateTrackAndTraceDomainEvent e);
}