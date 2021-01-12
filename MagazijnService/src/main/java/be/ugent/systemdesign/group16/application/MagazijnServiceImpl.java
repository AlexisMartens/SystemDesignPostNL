package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.GeenGeldigAdresException;
import be.ugent.systemdesign.group16.domain.Pakket;
import be.ugent.systemdesign.group16.domain.PakketRepository;
import be.ugent.systemdesign.group16.domain.PakketStatus;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;

@Transactional
@Service
public class MagazijnServiceImpl implements MagazijnService {

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	PakketRepository repo;

	@Override
	public Response BevestigInpakken(Integer _pakketId) {
		Pakket pakket = repo.findOne(_pakketId);
		pakket.UpdateTrackAndTrace();
		pakket.MaakNieuwSorteerItem();
		pakket.setStatus(PakketStatus.VERWERKT);
		repo.save(pakket);
		return new Response(ResponseStatus.SUCCESS, "id "+_pakketId);
	}
	
	@Override
	public Response UpdateTrackAndTrace(UpdateTrackAndTraceDomainEvent e) {
		if(repo.findOne(e.getPakketId())!=null) {
			eventPublisher.publishEvent(e);
		}
		return new Response(ResponseStatus.SUCCESS, "id "+e.getPakketId());
	}
	
	@Override
	public Response MaakPakket(Integer _pakketId, 
			String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, 
			String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender,
			String soort, boolean _ophalenBijKlant, boolean _spoed) {
		
		Pakket p = new Pakket(_pakketId, _naamOntvanger,_postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger,
					_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender,	_landAfzender,
					soort, _ophalenBijKlant, _spoed);
		return maakPakket(p);
	}
	
	@Override 
	public Response maakPakket(Pakket _p) {
		try {
			_p.setStatus(PakketStatus.AANGEMAAKT);
			Integer id = repo.save(_p);
			_p.setPakketId(id);
			repo.save(_p);			
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "Kon pakket niet aanmaken, message: "+e.getMessage());
		}
		return new Response(ResponseStatus.SUCCESS, "pakketId: "+_p.getPakketId());
	}
}
