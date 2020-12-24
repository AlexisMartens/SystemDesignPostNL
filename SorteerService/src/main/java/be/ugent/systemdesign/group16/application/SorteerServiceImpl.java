package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.SorteerderRepository;

@Service
@Transactional
public class SorteerServiceImpl implements SorteerService{

	@Autowired
	SorteerderRepository repo;
	
	@Override
	public void sorteer(Integer sorteerItemId, String naamHuidigeLocatie, String postcodeHuidigeLocatie,
			String straatHuidigeLocatie, String plaatsHuidigeLocatie, String landHuidigeLocatie, String naamDoel,
			String postcodeDoel, String straatDoel, String plaatsDoel, String landDoel, boolean spoed) {
		// TODO Auto-generated method stub
		
	}

}
