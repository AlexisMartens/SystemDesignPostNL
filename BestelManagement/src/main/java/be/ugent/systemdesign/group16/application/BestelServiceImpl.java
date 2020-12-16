package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.domain.*;
import be.ugent.systemdesign.group16.infrastructure.BestellingNotFoundException;

@Service
public class BestelServiceImpl implements BestelService {

	@Autowired
	BestellingRepository repo;
	
	@Override
	public Response plaatsBestelling( String _typeBestelling, String _naamOntvanger,
			String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger,
			String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender,
			String _landAfzender, boolean _spoed, boolean _extern, String _externeLeveringService) {
		Integer bestelId;
		try {
			Bestelling b = new Bestelling(_typeBestelling, _naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger,
					_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender,
					_landAfzender, _spoed, _extern, _externeLeveringService);
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
		try {
			Bestelling b = repo.findOne(_bestellingId);
			Bestelling retour = new Bestelling(b);
			repo.save(retour);
		} catch (BestellingNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"Geen bestelling om retour te sturen voor id "+_bestellingId);
		}catch (GeenGeldigeOntvangerException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd afleveradres");
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+_bestellingId);
	}

}
