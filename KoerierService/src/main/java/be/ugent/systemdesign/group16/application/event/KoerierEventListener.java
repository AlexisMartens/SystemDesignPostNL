package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.group16.domain.BevestigOphalenZendingEvent;
import be.ugent.systemdesign.group16.domain.BevestigAfleverenZendingEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceEvent;

@Service
public class KoerierEventListener {
	private static final Logger log = LoggerFactory.getLogger(KoerierEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleBevestigAfleverenZendingAsync(BevestigAfleverenZendingEvent event) {
		log.info(">handle handleBevestigAfleverenZending Async of event created at {}, with id {}", event.getCreatedTime(), event.getOrderId());
		eventDispatcher.publishBevestigAfleverenZendingEvent(event);
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleBevestigOphalenZendingAsync(BevestigOphalenZendingEvent event) {
		log.info(">handle handleBevestigOphalenZending Async of event created at {}, with id {}", event.getCreatedTime(), event.getOrderId());
		eventDispatcher.publishBevestigOphalenZendingEvent(event);
	}
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleUpdateTrackAndTraceEventAsync(UpdateTrackAndTraceEvent event) {
		log.info(">handle handleUpdateTrackAndTraceEvent Async of event created at {}, with id {}", event.getCreatedTime(), event.getOrderId());
		eventDispatcher.publishUpdateTrackAndTraceEvent(event);
	}
}
