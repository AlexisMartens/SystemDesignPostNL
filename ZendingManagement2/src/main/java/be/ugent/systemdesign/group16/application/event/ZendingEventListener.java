package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.group16.domain.KlaarVoorKoerierDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuwSorteerItemDomainEvent;


@Service
public class ZendingEventListener {
	private static final Logger log = LoggerFactory.getLogger(ZendingEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleKlaarVoorKoerierAsync(KlaarVoorKoerierDomainEvent event) {
		log.info(">handle KlaarVoorKoerier Async of event created at {}, with new status {} and id {}", event.getCreatedTime(), event.getStatus(), event.getZendingId());
		eventDispatcher.publishKlaarVoorKoerierEvent(event);
	}
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleNieuwSorteerItemAsync(NieuwSorteerItemDomainEvent event) {
		log.info(">handle NieuwSorteerItem Async of event created at {}, with new status {} and id {}", event.getCreatedTime(), event.getStatus(), event.getZendingId());
		eventDispatcher.publishNieuwSorteerItemEvent(event);
	}
}
