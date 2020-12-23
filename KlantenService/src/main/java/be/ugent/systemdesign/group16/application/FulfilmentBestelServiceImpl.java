package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.*;
import be.ugent.systemdesign.group16.infrastructure.BestellingNotFoundException;

@Transactional
@Service
public class FulfilmentBestelServiceImpl implements FulfilmentBestelService {

	@Autowired
	FulfilmentBestellingRepository repo;
	
	@Override
	public Response plaatsBestelling( FulfilmentKlant _b) {
		Integer bestelId;
		try {
			FulfilmentKlant b = new FulfilmentKlant(_b, false);
			repo.save(b);
			_b.setBestellingId(b.getBestellingId());
			b.verwerk();
			repo.save(b);
			bestelId = b.getBestellingId();
		} catch (GeenGeldigeOntvangerException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd afleveradres");
		}
		
		return new Response(ResponseStatus.SUCCESS,"id: "+bestelId);
	}

}
