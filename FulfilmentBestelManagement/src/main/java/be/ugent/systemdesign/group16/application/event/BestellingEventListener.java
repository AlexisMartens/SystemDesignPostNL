package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.group16.domain.GetKlantenDataDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuweTrackAndTraceDomainEvent;
import be.ugent.systemdesign.group16.domain.PacketDomainEvent;

@Service
public class BestellingEventListener {
	private static final Logger log = LoggerFactory.getLogger(BestellingEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleNieuweTrackAndTraceAsync(NieuweTrackAndTraceDomainEvent event) {
		log.info(">handle NieuweTrackAndTrace Async of event created at {}, with new status {} and id {}", event.getCreatedTime(), event.getStatus(), event.getBestellingId());
		eventDispatcher.publishNieuweTrackAndTraceEvent(event);
	}
	
	/*@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleGetKlantenDataAsync(GetKlantenDataDomainEvent event) {
		log.info(">handle ExterneBestelling Async of event created at {}, with new status {} and id {}", event.getCreatedTime(), event.getStatus(), event.getBestellingId());
		//eventDispatcher.publishNieuweTrackAndTraceEvent(event);
		eventDispatcher.publishExterneBestellingEvent(event);
	}*/
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePacketAsync(PacketDomainEvent event) {
		log.info(">handle Packet Async of event created at {} and id {}", event.getCreatedTime(), event.getBestellingId());
		//eventDispatcher.publishNieuweTrackAndTraceEvent(event);
		//eventDispatcher.publishExterneBestellingEvent(event);
		eventDispatcher.publishPacketEvent(event);
	}
}
