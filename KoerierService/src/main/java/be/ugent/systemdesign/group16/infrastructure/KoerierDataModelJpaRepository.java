package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KoerierDataModelJpaRepository extends JpaRepository<KoerierDataModel, Integer> {
	//the framework will automatically generate the appropriate SQL statement
	List<KoerierDataModel> findByPostcodeRonde(String PostcodeRonde);

}
