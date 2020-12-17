package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.TrackAndTraceService;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	TrackAndTraceService service;
	
	public void handleNewTrackAndTrace(NieuweTrackAndTraceDomainEvent event) {
		Response response = service.MaakTrackAndTrace(Integer.parseInt(event.getBestellingId()), event.getStatus());
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
}
