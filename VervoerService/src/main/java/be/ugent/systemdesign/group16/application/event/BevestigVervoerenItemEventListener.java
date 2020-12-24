package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.group16.domain.BevestigVervoerenItemDomainEvent;

@Service
public class BevestigVervoerenItemEventListener {

	private static final Logger log = LoggerFactory.getLogger(BevestigVervoerenItemEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleBevestigVervoerenItemDomainEvent(BevestigVervoerenItemDomainEvent e) {
		log.info("handle BevestigVervoerenItemDomainEvent created at: {}.", e.getCreatedAt());
		eventDispatcher.publishBevestigVervoerenItemDomainEvent(e);
	}
}
