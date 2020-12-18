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
	public Response bevestigAankomstNieuweZending( Zending _z) {
		Integer zendingId;
		try {
			Zending z = new Zending(_z, false);
			repo.save(z);
			_z.setZendingId(z.getZendingId());
			// TODO: eventuele methodes van Zending komen hier
			// bijvoorbeeld: z.Verwerk();
			repo.save(z);
			zendingId = z.getZendingId();
		} catch (GeenGeldigeOntvangerException e) {
			return new Response(ZendingStatus.FAIL,"Verkeerd afleveradres");
		}
		catch (GeenBestaandeExterneLeveringServiceException e) {
			return new Response(ResponseStatus.FAIL,"Onbestaande Externe Leverings Service");
		}
		
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

	@Override
	public Response bevestigAfhalen(Integer _zendingId) {
		Integer zendingId = null; 
		try {
			Zending z = repo.findOne(_zendingId);
			Zending retour = new Zending(z, true);
			repo.save(retour);
			zendingId = retour.getZendingId();
			//TODO: evetuele methodes
			//bijvoorbeeld: retour.Verwerk();
			repo.save(retour);
		} catch (ZendingNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"Geen zending om retour te sturen voor id " + zendingId);
		}catch (GeenGeldigeOntvangerException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd afleveradres");
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

}
