package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VervoerderDataModelJpaRepository extends JpaRepository<VervoerderDataModel,Integer>{
	List<VervoerderDataModel> findByStatus(String status);
}
