package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.application.event.NieuwSorteerItemEvent;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Soort;
import be.ugent.systemdesign.group16.domain.SorteerItem;
import be.ugent.systemdesign.group16.domain.SorteerItemNotFoundException;
import be.ugent.systemdesign.group16.domain.SorteerItemRepository;
import be.ugent.systemdesign.group16.domain.SorteerItemStatus;

@Transactional
@Service
public class SorteerItemServiceImpl implements SorteerItemService{
	
	@Autowired
	SorteerItemRepository repo;
	
	@Override
	public Response maakNieuwSorteerItem(Integer trackId, String naamDoel,
			String postcodeDoel, String straatDoel, String plaatsDoel, String landDoel, String naamAfkomst, String postcodeAfkomst,
			String straatAfkomst, String plaatsAfkomst, String landAfkomst, String naamHuidigeLocatie, String postcodeHuidigeLocatie,
			String straatHuidigeLocatie, String plaatsHuidigeLocatie, String landHuidigeLocatie, String soort, boolean spoed, LocalDate aanmaakDatum) {
		try {
			SorteerItem _s = makeSorteerItem(
					trackId,
					makeAdres(naamDoel, postcodeDoel, straatDoel, plaatsDoel, landDoel),
					makeAdres(naamAfkomst, postcodeAfkomst, straatAfkomst, plaatsAfkomst, landAfkomst),
					makeAdres(naamHuidigeLocatie, postcodeHuidigeLocatie, straatHuidigeLocatie, plaatsHuidigeLocatie, landHuidigeLocatie),
					soort,
					spoed,
					aanmaakDatum);
			repo.save(_s);
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "trackId: "+trackId);
		}
		return new Response(ResponseStatus.SUCCESS, "trackId: "+trackId);
	}
	
	@Override
	public Response gesorteerd(Integer sorteerItemId, String naamVolgendeLocatie, String postcodeVolgendeLocatie,
			String straatVolgendeLocatie, String plaatsVolgendeLocatie, String landVolgendeLocatie,
			boolean laatsteLocatie, Integer batchId) {
		try {
			SorteerItem _s = repo.findById(sorteerItemId);
			_s.maakKlaarVoorVervoer(makeAdres(naamVolgendeLocatie, postcodeVolgendeLocatie, straatVolgendeLocatie, 
					plaatsVolgendeLocatie, landVolgendeLocatie), laatsteLocatie, batchId);
			repo.save(_s);
		}
		catch(SorteerItemNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "SorteerItem met id : " + sorteerItemId + " not found.");
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "id: " + sorteerItemId);
		}
		return new Response(ResponseStatus.SUCCESS, "Gesorteerd id: " + sorteerItemId);
	}
	
	private static Adres makeAdres(String naam, String postcode, String straat, String plaats, String land) {
		return Adres.builder()
				.naam(naam)
				.postcode(postcode)
				.straat(straat)
				.plaats(plaats)
				.land(land)
				.build();
	}

	private static SorteerItem makeSorteerItem(Integer trackId, Adres doel, Adres afkomst, Adres huidigeLocatie,
			String soort, boolean spoed, LocalDate aanmaakDatum) {
		return SorteerItem.builder()
				.trackId(trackId)
				.doel(doel)
				.afkomst(afkomst)
				.huidigeLocatie(huidigeLocatie)
				.soort(Soort.valueOf(soort))
				.spoed(spoed)
				.status(SorteerItemStatus.IN_CENTRUM)
				.aanmaakDatum(aanmaakDatum)
				.build();
	}
}
