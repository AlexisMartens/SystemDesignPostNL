package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.TrackAndTrace;
import be.ugent.systemdesign.group16.domain.TrackAndTraceRepository;

@Transactional
@Service
public class TrackAndTraceServiceImpl implements TrackAndTraceService {

	@Autowired
	TrackAndTraceRepository repo;
	
	@Override
	public Response MaakTrackAndTrace(Integer bestellingId, String status) {
		if(repo.existsById(bestellingId)) {
			return new Response(ResponseStatus.FAIL, "id "+bestellingId);
		}
		repo.save(new TrackAndTrace(bestellingId, status));
		return new Response(ResponseStatus.SUCCESS, "id "+bestellingId);
	}

	@Override
	public Response UpdateTrackAndTrace(Integer bestellingId, String naam, String postcode, String straat,
			String plaats, String land, String status) {
		if(!repo.existsById(bestellingId)) {
			return new Response(ResponseStatus.FAIL, "id "+bestellingId);
		}
		TrackAndTrace tnt = repo.getOne(bestellingId);
		tnt.update(naam, postcode, straat, plaats, land, status);
		repo.save(tnt);
		return new Response(ResponseStatus.SUCCESS, "id "+bestellingId);
	}

	@Override
	public TrackAndTrace GetTrackAndTrace(Integer bestellingId) {
		 if (repo.findById(bestellingId).isPresent()) {
			 return repo.findById(bestellingId).get();
	     } else {
	         return null;
	     }
	}

}
