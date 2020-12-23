package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface SorteerItemRepository {
	
	List<SorteerItem> findAll();
	SorteerItem findById(Integer sorteerItemId);
	Integer save(SorteerItem _s);
	void delete(Integer sorteerItemId);
	
	//List<SorteerItem> findByStatus();
	//List<SorteerItem> findByHuidigeLocatie(Adres adres);
}
