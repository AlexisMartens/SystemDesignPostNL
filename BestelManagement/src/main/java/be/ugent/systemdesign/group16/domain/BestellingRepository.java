package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface BestellingRepository {

	public Bestelling findOne(Integer id);	
	public void save(Bestelling _b);	
	public List<Bestelling> findAllNietVerwerkt();
}
