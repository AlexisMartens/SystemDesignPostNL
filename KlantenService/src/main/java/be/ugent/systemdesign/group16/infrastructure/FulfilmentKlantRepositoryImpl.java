package be.ugent.systemdesign.group16.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.FulfilmentKlant;
import be.ugent.systemdesign.group16.domain.FulfilmentKlantRepository;

@Repository
public class FulfilmentKlantRepositoryImpl implements FulfilmentKlantRepository {

	@Autowired
	private FulfilmentKlantDataModelRepository klantenDMRepo;
	
	@Override
	public FulfilmentKlant findOne(Integer id) {
		FulfilmentKlantDataModel dm = klantenDMRepo.findById(id).orElseThrow(FulfilmentKlantNotFoundException::new);
		return mapToFulfilmentKlant(dm);
	}

	@Override
	public void save(FulfilmentKlant _k) {
		FulfilmentKlantDataModel dataModel = mapToFulfilmentKlantDataModel(_k);		
		klantenDMRepo.save(dataModel);
		_k.setKlantId(dataModel.getKlantId());
		klantenDMRepo.save(dataModel);
	}
	
	public void deleteById(Integer id) {
		klantenDMRepo.deleteById(id);
	}

	private FulfilmentKlantDataModel mapToFulfilmentKlantDataModel(FulfilmentKlant _k) {
		return new FulfilmentKlantDataModel(_k.getKlantId(),_k.getNaam());
	}
	
	private FulfilmentKlant mapToFulfilmentKlant(FulfilmentKlantDataModel _k) {
		FulfilmentKlant b = FulfilmentKlant.builder()
				.klantId(_k.getKlantId())
				.naam(_k.getNaam())
				.build();
		
		return b;
	}

}
