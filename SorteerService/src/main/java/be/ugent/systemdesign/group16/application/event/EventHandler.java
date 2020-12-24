package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.SorteerService;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

	@Autowired
	SorteerService service;
	
	public void handleStuurVervoerderEvent(StuurSorteerderEvent e) {
		log.info(">Handle StuurSorteerderEvent met id: {}.", e.getSorteerItemId());
		service.sorteer(e.getSorteerItemId(), e.getNaamHuidigeLocatie(), e.getPostcodeHuidigeLocatie(), e.getStraatHuidigeLocatie(), 
				e.getPlaatsHuidigeLocatie(), e.getLandHuidigeLocatie(), e.getNaamDoel(), e.getPostcodeDoel(),
				e.getStraatDoel(), e.getPlaatsDoel(), e.getLandDoel(), e.isSpoed());
	}
}
