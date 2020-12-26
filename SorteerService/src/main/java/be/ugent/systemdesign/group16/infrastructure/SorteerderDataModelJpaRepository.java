package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SorteerderDataModelJpaRepository extends JpaRepository<SorteerderDataModel, Integer>{
	List<SorteerderDataModel> findByStatusAndWerkLocatie(String status, SorteerCentrumDataModel werkLocatie);
}
