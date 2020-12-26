package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.group16.domain.BevestigSorterenItemDomainEvent;

@Service
public class BevestigSorterenItemEventListener {
	
	private static final Logger log = LoggerFactory.getLogger(BevestigSorterenItemEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleBevestigSorteerenItemDomainEvent(BevestigSorterenItemDomainEvent e) {
		log.info(">handle BevestigSorterenItemDomainEvent created at {}.", e.getCreatedAt());
		eventDispatcher.publishBevestigSorterenItemDomainEvent(e);
	}
}
