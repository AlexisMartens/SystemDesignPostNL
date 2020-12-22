package be.ugent.systemdesign.group16.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PakketRepository{
	public Pakket findOne(Integer id);	
	public void save(Pakket _p);	
	public List<Pakket> findAllAangemaakt();
}
