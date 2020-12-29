package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Pakket;
import be.ugent.systemdesign.group16.domain.PakketRepository;
import be.ugent.systemdesign.group16.domain.PakketStatus;

@Repository
public class PakketRepositoryImpl implements PakketRepository {

	@Autowired
	private PakketDataModelRepository pakketDMRepo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public Pakket findOne(Integer id) {
		PakketDataModel dm = pakketDMRepo.findById(id).orElseThrow(PakketNotFoundException::new);
		return mapToPakket(dm);
	}

	@Override
	public Integer save(Pakket _p) {
		Integer id = pakketDMRepo.save(mapToPakketDataModel(_p)).getPakketId();
	    _p.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
	    _p.clearDomainEvents();
	    return id;
	}

	private PakketDataModel mapToPakketDataModel(Pakket _p) {
		return new PakketDataModel(_p.getPakketId(), 
				_p.getAfzender().getNaam(), _p.getAfzender().getPostcode(), _p.getAfzender().getStraat(), _p.getAfzender().getPlaats(), _p.getAfzender().getLand(),
				_p.getOntvanger().getNaam(), _p.getOntvanger().getPostcode(), _p.getOntvanger().getStraat(), _p.getOntvanger().getPlaats(), _p.getOntvanger().getLand(),
				_p.getHuidigeLocatie().getNaam(),_p.getHuidigeLocatie().getPostcode(), _p.getHuidigeLocatie().getStraat(),_p.getHuidigeLocatie().getPlaats(), _p.getHuidigeLocatie().getLand()
				,_p.getSoort(), _p.isOphalenBijKlant(),_p.isSpoed(), _p.getStatus().name()
				);
	}
	
	private Pakket mapToPakket(PakketDataModel _p) {
		
		Pakket p = Pakket.builder()
				.pakketId(_p.getPakketId())
				.afzender(
						Adres.builder()
						.naam(_p.getNaamAfzender())
						.plaats(_p.getPlaatsAfzender())
						.postcode(_p.getPostcodeAfzender())
						.straat(_p.getStraatAfzender())
						.land(_p.getLandAfzender())
						.build()
						)
				.ontvanger(
						Adres.builder()
						.naam(_p.getNaamOntvanger())
						.plaats(_p.getPlaatsOntvanger())
						.postcode(_p.getPostcodeOntvanger())
						.straat(_p.getStraatOntvanger())
						.land(_p.getLandOntvanger())
						.build()
						)
				.huidigeLocatie(
						Adres.builder()
						.naam(_p.getNaamHuidigeLocatie())
						.plaats(_p.getPlaatsHuidigeLocatie())
						.postcode(_p.getPostcodeHuidigeLocatie())
						.straat(_p.getStraatHuidigeLocatie())
						.land(_p.getLandHuidigeLocatie())
						.build()
						)
				
				.soort(_p.getSoort())
				.ophalenBijKlant(_p.getOphalen())
				.spoed(_p.getSpoed())
				.status(PakketStatus.valueOf(_p.getStatus()))
				.build();
		return p;
	}

	@Override
	public List<Pakket> findAllAangemaakt() {
		return pakketDMRepo.findByStatus(PakketStatus.AANGEMAAKT.name())
				.stream()
				.map(elt -> mapToPakket(elt))
				.collect(Collectors.toList());
	}
}

