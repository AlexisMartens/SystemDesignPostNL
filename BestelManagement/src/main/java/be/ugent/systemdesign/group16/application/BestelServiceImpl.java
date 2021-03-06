package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.*;
import be.ugent.systemdesign.group16.infrastructure.BestellingNotFoundException;

@Transactional
@Service
public class BestelServiceImpl implements BestelService {

	@Autowired
	BestellingRepository repo;
	
	@Override
	public Response plaatsBestelling( Bestelling _b) {
		Integer bestelId;
		try {
			Bestelling b = new Bestelling(_b, false);
			repo.save(b);
			_b.setBestellingId(b.getBestellingId());
			b.Verwerk();
			repo.save(b);
			bestelId = b.getBestellingId();
		} catch (GeenGeldigeOntvangerException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd afleveradres");
		}
		catch (GeenBestaandeExterneLeveringServiceException e) {
			return new Response(ResponseStatus.FAIL,"Onbestaande Externe Leverings Service");
		}
		
		return new Response(ResponseStatus.SUCCESS,"id: "+bestelId);
	}

	@Override
	public Response plaatsRetour(Integer _bestellingId) {
		Integer bestelId = null; 
		try {
			Bestelling b = repo.findOne(_bestellingId);
			Bestelling retour = new Bestelling(b, true);
			repo.save(retour);
			bestelId = retour.getBestellingId();
			retour.Verwerk();
			repo.save(retour);
		} catch (BestellingNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"Geen bestelling om retour te sturen voor id " + bestelId);
		}catch (GeenGeldigeOntvangerException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd afleveradres");
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+bestelId);
	}

}
