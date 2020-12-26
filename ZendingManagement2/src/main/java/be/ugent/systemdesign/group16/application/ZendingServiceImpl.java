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
		// TODO Auto-generated method stub
		
		
		return null;
	}
	Integer bestelId;
	try {
		Bestelling b = new Bestelling(_b, false);
		repo.save(b);
		_b.setBestellingId(b.getBestellingId());
		b.Verwerk();
		repo.save(b);
		bestelId = b.getBestellingId();
	}
	@Override
	public Response maakNieuweZending(Zending _z) {
		try {
			// Methode geeft de PK terug, deze moet nog worden opgeslaan in het object.
			// Pas wanneer de sorteerItemId is toegekend, kan de aangekomenOpNieuweLocatie() gebeuren
			Zending z = new Zending(_z);
			repo.save(z);			
			z.setStatus(ZendingStatus.AANGEMAAKT);
			_z.setZendingId(z.getZendingId());
			_s.setSorteerItemId(id);
			_s.aangekomenOpNieuweLocatie(_s.getHuidigeLocatie());
			repo.save(_s);
			
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "Kon sorteerItem niet aanmaken, message: "+e.getMessage());
		}
	}
	
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
