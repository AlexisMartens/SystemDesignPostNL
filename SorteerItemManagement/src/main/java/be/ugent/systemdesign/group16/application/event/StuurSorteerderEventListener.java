package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.group16.domain.StuurSorteerderDomainEvent;

@Service
public class StuurSorteerderEventListener {

	private static final Logger log = LoggerFactory.getLogger(StuurSorteerderEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleStuurSorteerderDomainEvent(StuurSorteerderDomainEvent e) {
		log.info("handle StuurSorteerderDomainEvent created at {}.", e.getCreatedTime());
		eventDispatcher.publishStuurSorteerderDomainEvent(e);
	}
}
