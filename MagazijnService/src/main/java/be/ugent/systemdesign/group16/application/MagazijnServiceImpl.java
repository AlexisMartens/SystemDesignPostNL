package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.GeenGeldigAdresException;
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

		Pakket p = new Pakket(_naamOntvanger,_postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger,
					_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender,	_landAfzender,
					_naamHuidigeLocatie, _postcodeHuidigeLocatie, _straatHuidigeLocatie, _plaatsHuidigeLocatie, _landHuidigeLocatie,
					soort, spoed, status);
		p.UpdateTrackAndTrace();
		repo.save(p);
		return new Response(ResponseStatus.SUCCESS,"id: "+ p.getPakketId());
	}
	
	
	/* Het pakket is uit het magazijn gehaald, ingepakt en afgeleverd aan het sorteercentrum.*/
	// TODO: status nog updaten??
	@Override
	public Response BevestigInpakken(Integer _pakketId) {
		Pakket pakket = repo.findOne(_pakketId);
		pakket.UpdateTrackAndTrace();
		pakket.MaakNieuwSorteerItem();
		repo.save(pakket);
		return new Response(ResponseStatus.SUCCESS, "id "+_pakketId);
	}
}
