package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.GeenBeschikbareVervoerdersException;
import be.ugent.systemdesign.group16.domain.VervoerOrder;
import be.ugent.systemdesign.group16.domain.Vervoerder;
import be.ugent.systemdesign.group16.domain.VervoerderRepository;

@Service
@Transactional
public class VervoerServiceImpl implements VervoerService{

	@Autowired
	VervoerderRepository repo;

	@Override
	public Response vervoer(Integer sorteerItemId, String naamVan, String postcodeVan, String straatVan,
			String plaatsVan, String landVan, String naamNaar, String postcodeNaar, String straatNaar,
			String plaatsNaar, String landNaar, Integer batchId, boolean spoed) {
		try {
			// If spoed, eerst verwerken...
			Vervoerder _v = repo.findIdleVervoerders().get(0);
			_v.vervoer(new VervoerOrder(sorteerItemId,batchId,
					new Adres(naamVan, postcodeVan, straatVan, plaatsVan, landVan),
					new Adres(naamNaar, postcodeNaar, straatNaar, plaatsNaar, landNaar)));
			repo.save(_v);
		}
		catch(GeenBeschikbareVervoerdersException e) {
			new Response(ResponseStatus.FAIL, "Geen beschikbare vervoerder id: "+sorteerItemId);
		}
		catch(RuntimeException e) {
			new Response(ResponseStatus.FAIL, "id: "+sorteerItemId);
		}
		return 	new Response(ResponseStatus.SUCCESS, "id: "+sorteerItemId);
	}	
}
