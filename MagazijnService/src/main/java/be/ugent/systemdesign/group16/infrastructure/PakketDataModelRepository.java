package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PakketDataModelRepository extends JpaRepository<PakketDataModel, Integer> {

	public List<PakketDataModel> findByStatus(String status);
}
