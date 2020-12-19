package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ZendingDataModelRepository extends JpaRepository<ZendingDataModel, Integer> {

	public List<ZendingDataModel> findByStatus(String status);
}
