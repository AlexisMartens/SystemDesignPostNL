package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface MagazijnRepository {

	public Zending findOne(Integer id);	
	public void save(Zending _z);	
	//public List<Zending> findAllAfgehaald();
}
