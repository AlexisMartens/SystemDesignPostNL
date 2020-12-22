package be.ugent.systemdesign.group16.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.systemdesign.group16.domain.Koerier;

public interface OrderDataModelJpaRepository extends JpaRepository<OrderDataModel, Integer> {
	//the framework will automatically generate the appropriate SQL statement
	public Integer countByKoerierDataModel(KoerierDataModel k);

}
