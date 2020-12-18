package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.application.event.UpdateTrackAndTraceEvent;
import be.ugent.systemdesign.group16.domain.Bestelling;
import be.ugent.systemdesign.group16.domain.BestellingRepository;

@Transactional
@Service
public class ExterneLeveringServiceImpl implements ExterneLeveringService {

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	BestellingRepository repo;
	
	@Override
	public Response MaakBestelling(Integer _bestellingId, String _typeBestelling, String _naamOntvanger,
			String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger,
			String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender,
			String _landAfzender, boolean _ophalen, String _status, boolean _spoed, boolean _extern, String _externeLeveringService) {
		
		Bestelling newBestelling = new Bestelling(_bestellingId, _typeBestelling, _naamOntvanger,
				_postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger,
				_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender,
				_landAfzender, _ophalen, _status, _spoed, _extern, _externeLeveringService);
		repo.save(newBestelling);
		return new Response(ResponseStatus.SUCCESS, "id "+_bestellingId);
	}

	@Override
	public Response UpdateTrackAndTrace(UpdateTrackAndTraceEvent e) {
		if(repo.existsById(e.getBestellingId())) {
			eventPublisher.publishEvent(e);
		}
		return new Response(ResponseStatus.SUCCESS, "id "+e.getBestellingId());
	}

}
