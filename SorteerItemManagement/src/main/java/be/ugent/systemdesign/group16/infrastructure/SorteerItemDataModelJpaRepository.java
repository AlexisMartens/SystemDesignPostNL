package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SorteerItemDataModelJpaRepository extends JpaRepository<SorteerItemDataModel,Integer>{
	List<SorteerItemDataModel> findByStatus(String status);
	List<SorteerItemDataModel> findByHuidigeLocatie(AdresDataModel huidigeLocatie);
}