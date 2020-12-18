package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Service
public class TrackAndTraceEventListener {

	private static final Logger log = LoggerFactory.getLogger(TrackAndTraceEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@EventListener
	public void handleNieuweTrackAndTraceAsync(UpdateTrackAndTraceEvent event) {
		log.info(">handle NieuweTrackAndTrace Async of event created with new status {} and id {}", event.getStatus(), event.getBestellingId());
		eventDispatcher.publishUpdateTrackAndTraceEvent(event);
	}
}
