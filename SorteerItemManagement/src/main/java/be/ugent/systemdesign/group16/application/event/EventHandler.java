package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.SorteerItemService;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired 
	SorteerItemService service;
	
	public void handleNieuwSorteerItemEvent(NieuwSorteerItemEvent e) {
		log.info("Received NieuwSorteerItemEvent.");
	}
	
	public void handleBevestigSorterenEvent(BevestigSorterenEvent e) {
		log.info("Received BevestigSorterenEvent.");
	}
	
	public void handleBevestigVervoerenEvent(BevestigVervoerenEvent e) {
		log.info("Received BevestigVervoerenEvent.");
	}
}
