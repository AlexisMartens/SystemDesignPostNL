package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.VervoerService;
import be.ugent.systemdesign.group16.domain.Adres;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

	@Autowired
	VervoerService service;
	
	public void handleStuurVervoerderEvent(StuurVervoerderEvent e) {
		log.info(">Handle StuurVervoerderEvent.");
		service.vervoer(e.getSorteerItemId(), e.getNaamHuidigeLocatie(), e.getPostcodeHuidigeLocatie(), e.getStraatHuidigeLocatie(), 
				e.getPlaatsHuidigeLocatie(), e.getLandHuidigeLocatie(), e.getNaamVolgendeLocatie(), e.getPostcodeVolgendeLocatie(),
				e.getStraatVolgendeLocatie(), e.getPlaatsVolgendeLocatie(), e.getLandVolgendeLocatie(), e.getBatchId(), e.isSpoed());
	}
}
