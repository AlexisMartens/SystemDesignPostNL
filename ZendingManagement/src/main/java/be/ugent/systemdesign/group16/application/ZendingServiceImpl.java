package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.*;
import be.ugent.systemdesign.group16.infrastructure.ZendingNotFoundException;

@Transactional
@Service
public class ZendingServiceImpl implements ZendingService {

	@Autowired
	ZendingRepository repo;
	
	@Override
	public Response bevestigAankomstNieuweZending(Zending _z, String _huidigeLocatieNaam, String _huidigePostcode, String _huidigeStraat, String _huidigePlaats, String _huidigLand) {
		Integer zendingId;
		try {
			Zending z = new Zending(_z, _huidigeLocatieNaam, _huidigePostcode, _huidigeStraat, _huidigePlaats, _huidigLand);
			repo.save(z);
			_z.setZendingId(z.getZendingId());
			// TODO: eventuele methodes van Zending komen hier
			// bijvoorbeeld: z.Verwerk(); communicatie via events naar andere services
			repo.save(z);
			zendingId = z.getZendingId();
		} catch (GeenGeldigAdresException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd huidig adres opgegeven");
		}
		
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

	@Override
	public Response bevestigAfhalen(Integer _zendingId) {
		Integer zendingId = null; 
		try {
			Zending z = repo.findOne(_zendingId);
			repo.save(z);
			zendingId = z.getZendingId();
			//TODO: evetuele methodes
			//bijvoorbeeld: retour.Verwerk();
			repo.save(z);
		} catch (ZendingNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"Geen zending gevonden die kan worden afgehaald (door de klant) voor id " + zendingId);
		}catch (GeenGeldigAdresException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd huidig adres opgegeven");
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

}
