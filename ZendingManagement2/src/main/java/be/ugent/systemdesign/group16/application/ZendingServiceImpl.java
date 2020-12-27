package be.ugent.systemdesign.group16.application;

import java.util.Random;

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
	public Response bevestigAankomstNieuweZending(Integer _zendingId, Adres adresAfhaalpunt) {
		Integer zendingId = null; 
		try {			
			Zending z = repo.findOne(_zendingId);
			z.setStatus(ZendingStatus.AANGEMAAKT);
			z.setHuidigeLocatie(adresAfhaalpunt);
			repo.save(z);
			zendingId = z.getZendingId();
			z.stuurKoerier();
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
			z.setStatus(ZendingStatus.AFGELEVERD);
			repo.save(z);
			zendingId = z.getZendingId();		
		} catch (ZendingNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"Geen zending gevonden voor id " + zendingId);
		}catch (GeenGeldigAdresException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd huidig adres opgegeven");
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}
	
	@Override
	public Response maakNieuweZending(String _typeZending, String _naamOntvanger, String _postcodeOntvanger,
			String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, String _naamAfzender,
			String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender,
			boolean _ophalenBijKlant, boolean _spoed) {
		Integer zendingId = null;
		try {
			Zending z = new Zending(_typeZending, _naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger,
					_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender, _landAfzender, _ophalenBijKlant, _spoed);
			z.setStatus(ZendingStatus.AANGEMAAKT);
			zendingId = repo.save(z);	
			z.setZendingId(zendingId);
			repo.save(z);
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "Kon zending niet aanmaken, message: "+e.getMessage());
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

	@Override
	public Response maakNieuweZending(Zending _z) {
		Integer zendingId = null;
		try {
			_z.setStatus(ZendingStatus.AANGEMAAKT);
			zendingId = repo.save(_z);
			_z.setZendingId(zendingId);
			repo.save(_z);			
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "Kon zending niet aanmaken, message: "+e.getMessage());
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}
	
	/*bevestigafleverenzending ontvangen = kijken of het is afgeleverd bij klant (=gedaan) OF afgeleverd bij sorteercentrum dan moet ik nieuwsorteeritemdomainevent sturen
	 * Dan
	 * beveestigophalenzending niet op reageren */
	@Override
	public Response bevestigAfleverenZending(Integer _zendingId, Adres _huidigeLocatie) {	
		Integer zendingId = null; 
		try {
			Zending z = repo.findOne(_zendingId);	
			// Onderstaande zou moeten gecontroleerd worden of het bij de klant wordt afgeleverd
			// in plaats van random kans 
			Random r = new Random();
			int kansAfgeleverdBijKlant = r.nextInt(1);
			if(kansAfgeleverdBijKlant == 1) {
				z.setStatus(ZendingStatus.AFGELEVERD);
			}else {
				// nieuw sorteeritem maken 
				z.maakNieuwSorteerItem();
			}			
			repo.save(z);
			zendingId = z.getZendingId();
		} catch (ZendingNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"Geen zending gevonden voor id " + zendingId);
		}catch (GeenGeldigAdresException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd huidig adres opgegeven");
		}
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}

	//enkel status aanpassen
	@Override
	public Response bevestigOphalenZending(Integer _zendingId) {
		Integer zendingId = null; 
		try {			
			Zending z = repo.findOne(_zendingId);
			z.setStatus(ZendingStatus.OPGEHAALD);
			repo.save(z);
			zendingId = z.getZendingId();
			repo.save(z);
		} catch (GeenGeldigAdresException e) {
			return new Response(ResponseStatus.FAIL,"Verkeerd adres opgegeven");
		}		
		return new Response(ResponseStatus.SUCCESS,"id: "+zendingId);
	}
}
