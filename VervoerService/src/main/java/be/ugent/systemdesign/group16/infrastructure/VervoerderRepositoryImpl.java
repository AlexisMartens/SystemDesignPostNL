package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.GeenBeschikbareVervoerdersException;
import be.ugent.systemdesign.group16.domain.VervoerOrder;
import be.ugent.systemdesign.group16.domain.VervoerStatus;
import be.ugent.systemdesign.group16.domain.Vervoerder;
import be.ugent.systemdesign.group16.domain.VervoerderRepository;

@Repository
public class VervoerderRepositoryImpl implements VervoerderRepository{

	@Autowired
	VervoerderDataModelJpaRepository repo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Override
	public Integer save(Vervoerder _v) {
		Integer id = repo.save(mapToVervoerderDataModel(_v)).getVervoerderId();
			
		_v.getDomainEvents().forEach((e) -> eventPublisher.publishEvent(e));
		_v.clearEvents();
		return id;
	}
	
	@Override
	public List<Vervoerder> findIdleVervoerders() {
		List<VervoerderDataModel> idle = repo.findByStatus(VervoerStatus.IDLE.name());
		if(idle.size()==0) throw new GeenBeschikbareVervoerdersException();
		else
			return idle
					.stream()
					.map(v -> mapToVervoerder(v))
					.collect(Collectors.toUnmodifiableList());
	}
	
	private static VervoerOrderDataModel mapToVervoerOrderDataModel(VervoerOrder _o) {
		if(_o==null) return new VervoerOrderDataModel(0,0,"","","","","","","","","","");
		return new VervoerOrderDataModel(
				_o.getSorteerItemId(), _o.getBatchId(),
				_o.getVan().getNaam(), _o.getVan().getPostcode(), _o.getVan().getStraat(), _o.getVan().getPlaats(), _o.getVan().getLand(),
				_o.getNaar().getNaam(), _o.getNaar().getPostcode(), _o.getNaar().getStraat(), _o.getNaar().getPlaats(), _o.getNaar().getLand()
		);
	}
	
	private static VervoerderDataModel mapToVervoerderDataModel(Vervoerder _v) {
		VervoerderDataModel v = new VervoerderDataModel();
		v.setVervoerderId(_v.getVervoerderId());
		v.setNaam(_v.getNaam());
		v.setStatus(_v.getStatus().name());
		v.setOrder(mapToVervoerOrderDataModel(_v.getOrder()));
		return v;
	}
	
	private static VervoerOrder mapToVervoerOrder(VervoerOrderDataModel _o) {
		if(_o==null) return new VervoerOrder(0,0,new Adres("","","","",""),new Adres("","","","",""));
		return new VervoerOrder(_o.getSorteerItemId(), _o.getSorteerItemId(),
				new Adres(_o.getNaamVan(), _o.getPostcodeVan(), _o.getStraatVan(), _o.getPlaatsVan(), _o.getLandVan()),
				new Adres(_o.getNaamNaar(), _o.getPostcodeNaar(), _o.getStraatNaar(), _o.getPlaatsNaar(), _o.getLandNaar())
		);
	}
	
	private static Vervoerder mapToVervoerder(VervoerderDataModel v) {
		return new Vervoerder(v.getVervoerderId(), v.getNaam(), VervoerStatus.valueOf(v.getStatus()), mapToVervoerOrder(v.getOrder()));
	}
}
