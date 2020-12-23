package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.group16.domain.NieuwSorteerItemDomainEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;


@Service
public class PakketEventListener {
	private static final Logger log = LoggerFactory.getLogger(PakketEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleNieuwSorteerItemAsync(NieuwSorteerItemDomainEvent event) {
		log.info(">handle NieuwSorteerItem Async of event created at {}, with new status {} and id {}", event.getCreatedTime(), event.getPakketId());
		eventDispatcher.publishNieuwSorteerItemEvent(event);
	}
	
	@Async
	@EventListener
	public void handleNieuweTrackAndTraceAsync(UpdateTrackAndTraceDomainEvent event) {
		log.info(">handle NieuweTrackAndTrace Async of event created with new status {} and id {}", event.getStatus(), event.getPakketId());
		eventDispatcher.publishUpdateTrackAndTraceEvent(event);
	}
}
