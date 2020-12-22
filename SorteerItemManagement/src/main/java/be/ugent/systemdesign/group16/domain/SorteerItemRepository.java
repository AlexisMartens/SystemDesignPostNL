package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface SorteerItemRepository {
	List<SorteerItem> findAll();
	SorteerItem findById();
	List<SorteerItem> findByStatus();
	List<SorteerItem> findByHuidigeLocatie();
}
