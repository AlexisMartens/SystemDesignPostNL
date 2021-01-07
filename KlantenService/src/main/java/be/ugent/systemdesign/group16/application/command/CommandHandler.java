package be.ugent.systemdesign.group16.application.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.FulfilmentKlantService;

@Service
public class CommandHandler {
	
	private static final Logger log = LoggerFactory.getLogger(CommandHandler.class);
	
	@Autowired
	FulfilmentKlantService fulfilmentKlantService;
	
	public GetKlantenDataResponse getKlantenData(GetKlantenDataCommand command) {
		Integer id = Integer.parseInt(command.getKlantId());
		GetKlantenDataResponse response = fulfilmentKlantService.getKlantenData(id);
		return response;
	}
	
}