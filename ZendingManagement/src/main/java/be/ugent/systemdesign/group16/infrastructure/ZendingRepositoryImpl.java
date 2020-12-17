package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Zending;
import be.ugent.systemdesign.group16.domain.ZendingRepository;
import be.ugent.systemdesign.group16.domain.ZendingStatus;

@Repository
public class ZendingRepositoryImpl implements ZendingRepository {

	@Autowired
	private ZendingDataModelRepository zendingDMRepo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public Zending findOne(Integer id) {
		ZendingDataModel dm = zendingDMRepo.findById(id).orElseThrow(ZendingNotFoundException::new);
		return mapToZending(dm);
	}

	@Override
	public void save(Zending _z) {
		ZendingDataModel dataModel = mapToZendingDataModel(_z);		
		zendingDMRepo.save(dataModel);
		_z.setZendingId(dataModel.getZendingId());
		
		_z.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		_z.clearDomainEvents();
	}

	@Override
	public List<Zending> findAllNietVerwerkt() {
		return zendingDMRepo.findByStatus(ZendingStatus.AANGEMAAKT.name())
				.stream()
				.map(elt -> mapToZending(elt))
				.collect(Collectors.toList());
	}
	
	private ZendingDataModel mapToZendingDataModel(Zending _z) {
		return new ZendingDataModel(_z.getZendingId(),_z.getTypeZending(),_z.getAfzender().getNaam(), _z.getAfzender().getPostcode(), _z.getAfzender().getStraat(), _z.getAfzender().getPlaats(), _z.getAfzender().getLand(), _z.getOntvanger().getNaam(), _z.getOntvanger().getPostcode(), _z.getOntvanger().getStraat(), _z.getOntvanger().getPlaats(), _z.getOntvanger().getLand(),_z.isOphalen(), _z.getAanmaakDatum(), _z.getStatus().name(), _z.isSpoed(), _z.isExtern(), _z.getExterneLeveringService() == null? null : _z.getExterneLeveringService().name());
	}
	
	private Zending mapToZending(ZendingDataModel _z) {
		
		Zending z = Zending.builder()
				.zendingId(_z.getZendingId())
				.typeZending(_z.getTypeZending())
				.ontvanger(
						Adres.builder()
						.naam(_z.getNaamOntvanger())
						.plaats(_z.getPlaatsOntvanger())
						.postcode(_z.getPostcodeOntvanger())
						.straat(_z.getStraatOntvanger())
						.land(_z.getLandOntvanger())
						.build()
						)
				.afzender(
						Adres.builder()
						.naam(_z.getNaamAfzender())
						.plaats(_z.getPlaatsAfzender())
						.postcode(_z.getPostcodeAfzender())
						.straat(_z.getStraatAfzender())
						.land(_z.getLandAfzender())
						.build()
						)
				.ophalen(_z.isOphalen())
				.aanmaakDatum(_z.getAanmaakDatum())
				.status(ZendingStatus.valueOf(_z.getStatus()))
				.spoed(_z.getSpoed())
				.extern(_z.getExtern())
				.externeLeveringService( (_z.getExterneLeveringService() != null) ? ExterneLeveringService.valueOf(_z.getExterneLeveringService() ) : null)
				.build();
		
		return z;
	}

}
