package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface ZendingRepository {

	public Zending findOne(Integer id);	
	public Integer save(Zending _z);	
		
	public List<Zending> findAllAfgeleverd();
	public List<Zending> findAllOpTeHalen();
	public List<Zending> findAllOpgehaald();
	public List<Zending> findAllAangemaakt();
}