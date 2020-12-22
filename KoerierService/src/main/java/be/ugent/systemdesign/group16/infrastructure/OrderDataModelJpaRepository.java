package be.ugent.systemdesign.group16.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataModelJpaRepository extends JpaRepository<OrderDataModel, Integer> {
	
}
