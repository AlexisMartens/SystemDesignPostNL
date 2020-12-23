package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.SorteerItemService;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Soort;
import be.ugent.systemdesign.group16.domain.SorteerItem;
import be.ugent.systemdesign.group16.domain.SorteerItemStatus;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired 
	SorteerItemService service;
	
	public void handleNieuwSorteerItemEvent(NieuwSorteerItemEvent e) {
		log.info("Received NieuwSorteerItemEvent.");
		service.maakNieuwSorteerItem(mapToSorteerItem(e));
	}
	
	public void handleBevestigSorterenEvent(BevestigSorterenEvent e) {
		log.info("Received BevestigSorterenEvent.");
	}
	
	public void handleBevestigVervoerenEvent(BevestigVervoerenEvent e) {
		log.info("Received BevestigVervoerenEvent.");
	}
	
	private static SorteerItem mapToSorteerItem(NieuwSorteerItemEvent e) {
		return SorteerItem.builder()
				.doel(makeAdres(e.getNaamOntvanger(),e.getPostcodeOntvanger(), e.getStraatOntvanger(), e.getPlaatsOntvanger(),e.getLandOntvanger()))
				.afkomst(makeAdres(e.getNaamAfzender(), e.getPostcodeAfzender(), e.getStraatAfzender(), e.getPlaatsAfzender(), e.getLandAfzender()))
				.huidigeLocatie(makeAdres(e.getNaamHuidigeLocatie(), e.getPostcodeHuidigeLocatie(), e.getStraatHuidigeLocatie(), e.getPlaatsHuidigeLocatie(), e.getLandHuidigeLocatie()))
				.soort(Soort.valueOf(e.getTypeZending()))
				.spoed(e.isSpoed())
				.status(SorteerItemStatus.IN_CENTRUM)
				.aanmaakDatum(e.getAanmaakDatum())
				.build();
	}
	
	private static Adres makeAdres(String naam, String postcode, String straat, String plaats, String land) {
		return Adres.builder()
				.naam(naam)
				.postcode(postcode)
				.straat(straat)
				.plaats(plaats)
				.land(land)
				.build();
	}
}
