package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.GeenBeschikbareVervoerdersException;
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
	
	private static VervoerderDataModel mapToVervoerderDataModel(Vervoerder _v) {
		VervoerderDataModel v = new VervoerderDataModel();
		v.setVervoerderId(_v.getVervoerderId());
		v.setNaam(_v.getNaam());
		v.setStatus(_v.getStatus().name());
		return v;
	}
	
	private static Vervoerder mapToVervoerder(VervoerderDataModel v) {
		return new Vervoerder(v.getVervoerderId(), v.getNaam(), VervoerStatus.valueOf(v.getStatus()), null);
	}
}
