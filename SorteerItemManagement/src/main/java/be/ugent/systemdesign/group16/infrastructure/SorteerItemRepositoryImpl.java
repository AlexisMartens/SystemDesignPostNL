package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Soort;
import be.ugent.systemdesign.group16.domain.SorteerItem;
import be.ugent.systemdesign.group16.domain.SorteerItemNotFoundException;
import be.ugent.systemdesign.group16.domain.SorteerItemRepository;
import be.ugent.systemdesign.group16.domain.SorteerItemStatus;

@Repository
public class SorteerItemRepositoryImpl implements SorteerItemRepository{

	@Autowired
	SorteerItemDataModelJpaRepository repo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public List<SorteerItem> findAll() {
		return mapToSorteerItems(repo.findAll());
	}

	@Override
	public SorteerItem findById(Integer sorteerItemId) {
		return mapToSorteerItem(repo.findById(sorteerItemId).orElseThrow(SorteerItemNotFoundException::new));
	}

	@Override
	public Integer save(SorteerItem _s) {
		Integer id = repo.save(mapToSorteerItemDataModel(_s)).getSorteerItemId();
		repo.findById(id).ifPresent((sorteerItem) -> sorteerItem.setSorteerItemId(id));
		
		_s.getDomainEvents().forEach((event) -> eventPublisher.publishEvent(event));
		_s.clearEvents();
		return id;
	}

	@Override
	public void delete(Integer sorteerItemId) {
		repo.deleteById(sorteerItemId);
	}

	private static Adres mapToAdres(AdresDataModel a) {
		return Adres.builder()
				.naam(a.getNaam())
				.postcode(a.getPostcode())
				.straat(a.getStraat())
				.plaats(a.getPlaats())
				.land(a.getLand())
				.build();
	}
	
	private static AdresDataModel mapToAdresDataModel(Adres a) {
		AdresDataModel adres = new AdresDataModel();
		adres.setNaam(a.getNaam());
		adres.setPostcode(a.getPostcode());
		adres.setStraat(a.getStraat());
		adres.setPlaats(a.getPlaats());
		adres.setLand(a.getLand());
		return adres;
	}
	
	private static SorteerItem mapToSorteerItem(SorteerItemDataModel s) {
		return SorteerItem.builder()
				.sorteerItemId(s.getSorteerItemId())
				.trackId(s.getTrackId())
				.doel(mapToAdres(s.getDoel()))
				.afkomst(mapToAdres(s.getAfkomst()))
				.huidigeLocatie(mapToAdres(s.getHuidigeLocatie()))
				.soort(Soort.valueOf(s.getSoort()))
				.spoed(s.isSpoed())
				.status(SorteerItemStatus.valueOf(s.getStatus()))
				.aanmaakDatum(s.getAanmaakDatum())
				.build();
	}
	
	private static List<SorteerItem> mapToSorteerItems(List<SorteerItemDataModel> items){
		return items.stream()
			.map(item -> mapToSorteerItem(item))
			.collect(Collectors.toUnmodifiableList());
	}
	
	private static SorteerItemDataModel mapToSorteerItemDataModel(SorteerItem item){
		return new SorteerItemDataModel(item.getTrackId(), mapToAdresDataModel(item.getDoel()), mapToAdresDataModel(item.getAfkomst()), 
						mapToAdresDataModel(item.getHuidigeLocatie()), item.getSoort().name(), item.isSpoed(), item.getStatus().name(), item.getAanmaakDatum());
	}

}
