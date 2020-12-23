package be.ugent.systemdesign.group16.application.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.ResponseStatus;
import be.ugent.systemdesign.group16.domain.FulfilmentBestellingRepository;

@Service
public class CommandHandler {
	
	private static final Logger log = LoggerFactory.getLogger(CommandHandler.class);
	
	@Autowired
	FulfilmentBestellingRepository repo;
	
	public void handleGetKlantenDataResponse(GetKlantenDataResponse response) {
		log.info("klantId {}, naam {}", response.getKlantenId(), response.getNaam());
	}
	
}