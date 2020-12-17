package be.ugent.systemdesign.group16.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellingDataModelRepository extends JpaRepository<BestellingDataModel, Integer> {

	public List<BestellingDataModel> findByStatus(String status);
}
