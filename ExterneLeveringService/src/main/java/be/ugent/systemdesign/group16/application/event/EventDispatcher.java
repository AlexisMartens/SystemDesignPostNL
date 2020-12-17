package be.ugent.systemdesign.group16.application.event;



public interface EventDispatcher {

	void publishUpdateTrackAndTraceEvent(UpdateTrackAndTraceEvent event);
}
