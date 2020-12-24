package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SorteerCentrumDataModelJpaRepository extends JpaRepository<SorteerCentrumDataModel, Integer>{
	List<SorteerCentrumDataModel> findByAdres(AdresDataModel adres);
}