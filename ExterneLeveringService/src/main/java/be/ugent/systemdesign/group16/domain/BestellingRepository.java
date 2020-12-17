package be.ugent.systemdesign.group16.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellingRepository extends JpaRepository<Bestelling, Integer> {

	public List<Bestelling> findByStatus(String status);
}
