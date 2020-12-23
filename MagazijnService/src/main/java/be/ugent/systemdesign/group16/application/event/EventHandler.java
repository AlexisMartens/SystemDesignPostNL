package be.ugent.systemdesign.group16.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.MagazijnService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.domain.Adres;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	MagazijnService service;
	//bevestigOphalenZending
	//bevestigAfleverenZending
	//nieuwezending sorteeritemmgmt
	public void handleNieuwPakket(NieuwPakketDomainEventMOETIKKOPIERENVANFULLFILMENTMGMT event) {
		log.info("-Aangekomen zending");
		Response response; 

		// ophalen bij klant thuis
		if(event.isOphalen()) {
			response = service.bevestigAankomstNieuweZending(event.getBestellingId(), new Adres(event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
					event.getPlaatsOntvanger(), event.getLandOntvanger()));
		}
		// zending moet naar afhaalpunt
		else {
			response = service.bevestigAankomstNieuweZending(event.getBestellingId(), new Adres(event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(),
					event.getPlaatsOntvanger(), event.getLandOntvanger()));
		}
				/*(new Zending(event.getTypeBestelling(), null, null, null, null, null,
				event.getNaamOntvanger(), event.getPostcodeOntvanger(), event.getStraatOntvanger(), event.getPlaatsOntvanger(), event.getLandOntvanger(),
				event.getNaamAfzender(), event.getPostcodeAfzend*er(), event.getStraatAfzender(), event.getPlaatsAfzender(), event.getLandAfzender(),
				event.isOphalen(), event.isSpoed()))*/
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	
}