package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface ZendingRepository {

	public Zending findOne(Integer id);	
	public void save(Zending _z);	
	// TODO: check functienamen
	public List<Zending> findAllAfgehaald();
	public List<Zending> findAllAfTeHalen();
	public List<Zending> findAllOpTeHalenBijKlant();
}
