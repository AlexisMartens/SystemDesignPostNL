package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.BevestigVervoerenItemDomainEvent;

public interface EventDispatcher {
	
	void publishBevestigVervoerenItemDomainEvent(BevestigVervoerenItemDomainEvent e);
}
