package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface KoerierRepository {

	public Koerier findOne(Integer id);	
	public void save(Koerier k);	
	public List<Koerier> findByPostcodeRonde(String postcodeRonde);
	
}
