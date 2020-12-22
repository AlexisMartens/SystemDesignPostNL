package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.KoerierRepository;

@Repository
public class KoerierRepositoryImpl implements KoerierRepository {

	@Autowired
	KoerierDataModelJpaRepository koerierDMJPARepo;
	
	@Override
	public Koerier findOne(Integer id) {
		KoerierDataModel dataModel = koerierDMJPARepo.findById(id).orElseThrow(KoerierNotFoundException::new);
		return mapToKoerier(dataModel);	
	}

	@Override
	public void save(Koerier koerier) {
		KoerierDataModel dataModel = mapToKoerierDataModel(koerier);		
		koerierDMJPARepo.save(dataModel);		
	}
	
	@Override
	public List<Koerier> findByPostcodeRonde(String PostcodeRonde) {
		return koerierDMJPARepo.findByPostcodeRonde(PostcodeRonde)
			.stream()
			.map(elt -> mapToKoerier(elt))
			.collect(Collectors.toList());
	}

	private KoerierDataModel mapToKoerierDataModel(Koerier koerier) {
		return new KoerierDataModel(koerier.getKoerierId(),koerier.getNaam(),koerier.getPostcodeRonde(),koerier.getVervoercapaciteit());
	}
	
	private Koerier mapToKoerier(KoerierDataModel koerierDM) {
		Koerier k = Koerier.builder()
						.koerierId(koerierDM.getKoerierId())
						.naam(koerierDM.getNaam())
						.postcodeRonde(koerierDM.getPostcodeRonde())
						.vervoercapaciteit(koerierDM.getVervoercapaciteit())
						.build();
		return k;
	}
}
