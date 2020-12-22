package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.Bestelling;
import be.ugent.systemdesign.group16.domain.GeenBestaandeExterneLeveringServiceException;
import be.ugent.systemdesign.group16.domain.GeenGeldigAdresException;
import be.ugent.systemdesign.group16.domain.GeenGeldigeOntvangerException;
import be.ugent.systemdesign.group16.domain.Pakket;
import be.ugent.systemdesign.group16.domain.PakketRepository;


@Transactional
@Service
public class MagazijnServiceImpl implements MagazijnService {

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	PakketRepository repo;
	
	
	
	
	
	@Override
	public Response MaakPakket(Integer _pakketId, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, 
			String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender,
			String _naamHuidigeLocatie, String _postcodeHuidigeLocatie, String _straatHuidigeLocatie, String _plaatsHuidigeLocatie, String _landHuidigeLocatie,
			String soort, boolean spoed, String status) {
		Integer pakketId;
		
		Pakket p = new Pakket(_naamOntvanger,_postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger,
					_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender,	_landAfzender,
					_naamHuidigeLocatie, _postcodeHuidigeLocatie, _straatHuidigeLocatie, _plaatsHuidigeLocatie, _landHuidigeLocatie,
					soort, spoed, status);
		repo.save(p);
		_b.setPakketId(p.getBestellingId());
		//b.Verwerk();
		repo.save(b);
		bestelId = b.getBestellingId();
		
		return new Response(ResponseStatus.SUCCESS,"id: "+_pakketId);
		
		
		
		
		
		if(repo.existsById(_pakketId)) {
			return new Response(ResponseStatus.FAIL, "id "+_pakketId);
		}

		return new Response(ResponseStatus.SUCCESS, "id "+_pakketId);
	}
	
	
	/* Het pakket is uit het magazijn gehaald, ingepakt en afgeleverd aan het sorteercentrum.
	 */
	// TODO: verwerk methode?
	@Override
	public Response BevestigInpakken(Integer _pakketId) {
		if(!repo.existsById(_pakketId)) {
			return new Response(ResponseStatus.FAIL, "id "+_pakketId);
		}
		
		Pakket pakket = repo.getOne(_pakketId);
		//pakket.setGrootte(grootte);
		//update status
		//pakket.update(qsdkfdsqkfdqsf,;ds;fsd);
		repo.save(pakket);
		return new Response(ResponseStatus.SUCCESS, "id "+_pakketId);
}
	

	
	/*@Override
	public Response UpdateTrackAndTrace(UpdateTrackAndTraceEvent e) {
		if(repo.existsById(e.getBestellingId())) {
			eventPublisher.publishEvent(e);
		}
		return new Response(ResponseStatus.SUCCESS, "id "+e.getBestellingId());
	}*/

}
