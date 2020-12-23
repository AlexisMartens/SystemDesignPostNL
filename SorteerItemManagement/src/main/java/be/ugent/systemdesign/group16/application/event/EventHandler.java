package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.SorteerItemService;
import be.ugent.systemdesign.group16.domain.Adres;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired 
	SorteerItemService service;
	
	public void handleNieuwSorteerItemEvent(NieuwSorteerItemEvent e) {
		log.info("Received NieuwSorteerItemEvent.");
		service.maakNieuwSorteerItem(e.getTrackId(), e.getNaamOntvanger(), e.getPostcodeOntvanger(), e.getStraatOntvanger(), 
				e.getPlaatsOntvanger(), e.getLandOntvanger(), e.getNaamAfzender(), e.getPostcodeAfzender(), 
				e.getStraatAfzender(), e.getPlaatsAfzender(), e.getLandAfzender(), e.getNaamHuidigeLocatie(), 
				e.getPostcodeHuidigeLocatie(), e.getStraatHuidigeLocatie(), e.getPlaatsHuidigeLocatie(), 
				e.getLandHuidigeLocatie(), e.getTypeZending(), e.isSpoed(), e.getAanmaakDatum());
	}
	
	public void handleBevestigSorterenEvent(BevestigSorterenEvent e) {
		log.info("Received BevestigSorterenEvent.");
		service.gesorteerd(e.getSorteerItemId(), e.getNaamVolgendeLocatie(), e.getPostcodeVolgendeLocatie(), e.getStraatVolgendeLocatie(), 
				e.getPlaatsVolgendeLocatie(), e.getLandVolgendeLocatie(), e.isLaatsteLocatie(), e.getBatchId());
	}
	
	public void handleBevestigVervoerenEvent(BevestigVervoerenEvent e) {
		log.info("Received BevestigVervoerenEvent.");
		service.vervoerd(e.getSorteerItemId(), e.getNaamNieuweLocatie(), e.getPostcodeNieuweLocatie(), e.getStraatNieuweLocatie(), 
				e.getPlaatsNieuweLocatie(), e.getLandNieuweLocatie());
	}
}
