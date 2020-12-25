package be.ugent.systemdesign.group16.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.GeenBeschikbareSorteerdersException;
import be.ugent.systemdesign.group16.domain.SorteerBestemming;
import be.ugent.systemdesign.group16.domain.SorteerOrder;
import be.ugent.systemdesign.group16.domain.Sorteerder;
import be.ugent.systemdesign.group16.domain.SorteerderRepository;

@Service
@Transactional
public class SorteerServiceImpl implements SorteerService{
	
	@Autowired
	SorteerderRepository repo;
	
	@Override
	public Response sorteer(Integer sorteerItemId, String naamHuidigeLocatie, String postcodeHuidigeLocatie,
			String straatHuidigeLocatie, String plaatsHuidigeLocatie, String landHuidigeLocatie, String naamDoel,
			String postcodeDoel, String straatDoel, String plaatsDoel, String landDoel, boolean spoed) {
		try {
			Sorteerder s = repo.findIdleSorteerdersAtCentrum(new Adres(naamHuidigeLocatie,postcodeHuidigeLocatie,straatHuidigeLocatie,plaatsHuidigeLocatie,landHuidigeLocatie)).get(0);
			SorteerBestemming b = s.getWerkLocatie().bepaalVolgendeLocatie(new Adres(naamDoel,postcodeDoel,straatDoel,plaatsDoel,landDoel));
			s.sorteer(new SorteerOrder(sorteerItemId,b.getBatchId(),b.getVolgendeLocatie(),spoed,b.isEindBestemming()));
			repo.save(s);
		}
		catch(GeenBeschikbareSorteerdersException e) {
			return new Response(ResponseStatus.FAIL, "Geen beschikbare sorteerders gevonden op locatie voor id: "+sorteerItemId);
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "id: "+sorteerItemId);
		}
		return new Response(ResponseStatus.SUCCESS, "sorteren voor id gelukt: "+sorteerItemId);
	}

}
