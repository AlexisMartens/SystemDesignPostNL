package be.ugent.systemdesign.group16.application.event;


import be.ugent.systemdesign.group16.domain.BevestigAfleverenZendingEvent;
import be.ugent.systemdesign.group16.domain.BevestigOphalenZendingEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceEvent;

public interface EventDispatcher {

	void publishBevestigOphalenZendingEvent(BevestigOphalenZendingEvent event);
	
	void publishBevestigAfleverenZendingEvent(BevestigAfleverenZendingEvent event);
	 
	void publishUpdateTrackAndTraceEvent(UpdateTrackAndTraceEvent event);

}
