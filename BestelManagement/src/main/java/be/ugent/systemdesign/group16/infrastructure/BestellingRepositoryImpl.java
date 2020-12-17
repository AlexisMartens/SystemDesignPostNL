package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Bestelling;
import be.ugent.systemdesign.group16.domain.BestellingRepository;
import be.ugent.systemdesign.group16.domain.ExterneLeveringService;
import be.ugent.systemdesign.group16.domain.BestellingStatus;

@Repository
public class BestellingRepositoryImpl implements BestellingRepository {

	@Autowired
	private BestellingDataModelRepository bestellingDMRepo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public Bestelling findOne(Integer id) {
		BestellingDataModel dm = bestellingDMRepo.findById(id).orElseThrow(BestellingNotFoundException::new);
		return mapToBestelling(dm);
	}

	@Override
	public void save(Bestelling _b) {
		BestellingDataModel dataModel = mapToBestellingDataModel(_b);		
		bestellingDMRepo.save(dataModel);
		_b.setBestellingId(dataModel.getBestellingId());
		
		_b.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		_b.clearDomainEvents();
	}

	@Override
	public List<Bestelling> findAllNietVerwerkt() {
		return bestellingDMRepo.findByStatus(BestellingStatus.AANGEMAAKT.name())
				.stream()
				.map(elt -> mapToBestelling(elt))
				.collect(Collectors.toList());
	}
	
	private BestellingDataModel mapToBestellingDataModel(Bestelling _b) {
		return new BestellingDataModel(_b.getBestellingId(),_b.getTypeBestelling(),_b.getAfzender().getNaam(), _b.getAfzender().getPostcode(), _b.getAfzender().getStraat(), _b.getAfzender().getPlaats(), _b.getAfzender().getLand(), _b.getOntvanger().getNaam(), _b.getOntvanger().getPostcode(), _b.getOntvanger().getStraat(), _b.getOntvanger().getPlaats(), _b.getOntvanger().getLand(), _b.getAanmaakDatum(), _b.getStatus().name(), _b.isSpoed(), _b.isExtern(), _b.getExterneLeveringService() == null? null : _b.getExterneLeveringService().name());
	}
	
	private Bestelling mapToBestelling(BestellingDataModel _b) {
		
		Bestelling b = Bestelling.builder()
				.bestellingId(_b.getBestellingId())
				.typeBestelling(_b.getTypeBestelling())
				.ontvanger(
						Adres.builder()
						.naam(_b.getNaamOntvanger())
						.plaats(_b.getPlaatsOntvanger())
						.postcode(_b.getPostcodeOntvanger())
						.straat(_b.getStraatOntvanger())
						.land(_b.getLandOntvanger())
						.build()
						)
				.afzender(
						Adres.builder()
						.naam(_b.getNaamAfzender())
						.plaats(_b.getPlaatsAfzender())
						.postcode(_b.getPostcodeAfzender())
						.straat(_b.getStraatAfzender())
						.land(_b.getLandAfzender())
						.build()
						)
				.aanmaakDatum(_b.getAanmaakDatum())
				.status(BestellingStatus.valueOf(_b.getStatus()))
				.spoed(_b.getSpoed())
				.extern(_b.getExtern())
				.externeLeveringService( (_b.getExterneLeveringService() != null) ? ExterneLeveringService.valueOf(_b.getExterneLeveringService() ) : null)
				.build();
		
		return b;
	}

}
