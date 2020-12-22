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
	public Response bevestigAankomstNieuweZending(Zending _z) {
		Integer zendingId;
		try {
			Zending z = new Zending(_z);
			repo.save(z);
			_z.setZendingId(z.getZendingId());
			z.Verwerk();
			repo.save(z);
			zendingId = z.getZendingId();
			repo.save(z);
		} catch (GeenGeldigAdresException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd adres opgegeven");
		}
		
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

	@Override
	public Response bevestigAfhalen(Integer _zendingId) {
		Integer zendingId = null; 
		try {
			Zending z = repo.findOne(_zendingId);
			//TODO: beschermen tegen geval: als je bevestigAfhalen oproept op een zending waarbij Zending wordt opgehaald bij klant thuis??
			z.setStatus(ZendingStatus.AFGEHAALD_IN_AFHAALPUNT);
			repo.save(z);
			zendingId = z.getZendingId();
			z.Verwerk();
			repo.save(z);
		} catch (ZendingNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"Geen zending gevonden voor id " + zendingId);
		}catch (GeenGeldigAdresException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd huidig adres opgegeven");
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

}