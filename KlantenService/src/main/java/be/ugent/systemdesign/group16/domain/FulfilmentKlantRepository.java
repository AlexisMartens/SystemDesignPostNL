package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface FulfilmentKlantRepository {

	public FulfilmentKlant findOne(Integer id);	
	public void save(FulfilmentKlant _b);	
	public void deleteById(Integer id);
}
