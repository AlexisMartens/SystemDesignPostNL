package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.GeenBeschikbareSorteerdersException;
import be.ugent.systemdesign.group16.domain.SorteerCentrum;
import be.ugent.systemdesign.group16.domain.SorteerOrder;
import be.ugent.systemdesign.group16.domain.Sorteerder;
import be.ugent.systemdesign.group16.domain.SorteerderRepository;
import be.ugent.systemdesign.group16.domain.SorteerderStatus;

@Repository
public class SorteerderRepositoryImpl implements SorteerderRepository{

	@Autowired
	SorteerderDataModelJpaRepository repo;
	
	@Autowired
	SorteerCentrumDataModelJpaRepository centrumRepo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public Integer save(Sorteerder _s) {
		_s.getWerkLocatie().setLocatieId(save(_s.getWerkLocatie()));
		SorteerCentrumDataModel centrum = centrumRepo.findById(_s.getWerkLocatie().getLocatieId()).orElseThrow(RuntimeException::new);
		Integer id = repo.save(mapToSorteerderDataModel(_s, centrum)).getSorteerderId();
		
		_s.getEvents().forEach(e -> eventPublisher.publishEvent(e));
		_s.clearEvents();
		return id;
	}
	
	@Override
	public Integer save(SorteerCentrum c) {
		return centrumRepo.saveAndFlush(mapToSorteerCentrumDataModel(c)).getLocatieId();
	}

	
	@Override
	public List<Sorteerder> findAll() {
		return repo.findAll()
				.stream()
				.map(i -> mapToSorteerder(i))
				.collect(Collectors.toList());
	}

	@Override
	public List<Sorteerder> findIdleSorteerdersAtCentrum(Adres a) {
		SorteerCentrumDataModel c;
		try {
			c = centrumRepo.findByAdres(mapToAdresDataModel(a)).get(0);
		}
		catch(RuntimeException e) {
			throw new GeenBeschikbareSorteerdersException();
		}
		List<SorteerderDataModel> l = repo.findByStatusAndWerkLocatie(SorteerderStatus.IDLE.name(), c);
		if(l.size()==0) throw new GeenBeschikbareSorteerdersException();
		else
			return l.stream()
					.map(i -> mapToSorteerder(i))
					.collect(Collectors.toList());
	}

	private static SorteerderDataModel mapToSorteerderDataModel(Sorteerder s, SorteerCentrumDataModel c) {
		SorteerderDataModel r = new SorteerderDataModel();
		r.setNaam(s.getNaam());
		r.setOrder(mapToSorteerOrderDataModel(s.getOrder()));
		r.setStatus(s.getStatus().name());
		r.setWerkLocatie(c);
		return r;
	}
	
	private static Sorteerder mapToSorteerder(SorteerderDataModel s) {
		return new Sorteerder(s.getSorteerderId(), s.getNaam(), SorteerderStatus.valueOf(s.getStatus()), mapToSorteerOrder(s.getOrder()), mapToSorteerCentrum(s.getWerkLocatie()));
	}
	
	private static SorteerOrderDataModel mapToSorteerOrderDataModel(SorteerOrder o) {
		if(o==null || o.getDoel() == null) return new SorteerOrderDataModel();
		return new SorteerOrderDataModel(o.getSorteerItemId(), o.getBatchId(),o.isSpoed(),o.isEindBestemming(),
				o.getDoel().getNaam(),o.getDoel().getPostcode(),o.getDoel().getStraat(),o.getDoel().getPostcode(),o.getDoel().getLand());
	}
	
	private static AdresDataModel mapToAdresDataModel(Adres a) {
		return new AdresDataModel(a.getNaam(),a.getPostcode(),a.getStraat(),a.getPlaats(),a.getLand());
	}
	
	private static Adres mapToAdres(AdresDataModel a) {
		return new Adres(a.getNaam(),a.getPostcode(),a.getStraat(),a.getPlaats(),a.getLand());
	}
	
	private static SorteerOrder mapToSorteerOrder(SorteerOrderDataModel o) {
		if(o==null) return new SorteerOrder();
		return new SorteerOrder(o.getSorteerItemId(), o.getBatchId(), 
				new Adres(o.getNaamDoel(), o.getPostcodeDoel(),o.getStraatDoel(),o.getPlaatsDoel(),o.getLandDoel()),
				o.isSpoed(), o.isEindBestemming());
	}
	
	private static SorteerCentrum mapToSorteerCentrum(SorteerCentrumDataModel c) {
		return new SorteerCentrum(c.getLocatieId(),mapToAdres(c.getAdres()), mapToAdres(c.getVolgendeLocatie()));
	}
	
	private static SorteerCentrumDataModel mapToSorteerCentrumDataModel(SorteerCentrum c) {
		return new SorteerCentrumDataModel(c.getLocatieId(),mapToAdresDataModel(c.getEigenLocatie()), mapToAdresDataModel(c.getVolgendeLocatie()));
	}
}
