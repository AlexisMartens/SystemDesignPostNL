package be.ugent.systemdesign.group16.application;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.application.command.GetKlantenDataCommand;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataResponse;
import be.ugent.systemdesign.group16.domain.*;
import be.ugent.systemdesign.group16.infrastructure.FulfilmentKlantNotFoundException;

@Transactional
@Service
public class FulfilmentKlantServiceImpl implements FulfilmentKlantService {

	@Autowired
	FulfilmentKlantRepository repo;
	
	@Override
	public Response maakFulfilmentKlant(FulfilmentKlant k) {
		Integer id;
		FulfilmentKlant new_k = new FulfilmentKlant(k.getKlantId(), k.getNaam());
		repo.save(new_k);
		return new Response(ResponseStatus.SUCCESS,"new id: "+new_k.getKlantId());
	}
	
	@Override
	public Response stopFulfilmentKlant(Integer id) {
		repo.deleteById(id);
		return new Response(ResponseStatus.SUCCESS,"deleted id: "+id);
	}
	
	@Override
	public GetKlantenDataResponse getKlantenData(String id) {
		FulfilmentKlant k = repo.findOne(Integer.parseInt(id));
		return new GetKlantenDataResponse("deleted id: "+id,ResponseStatus.SUCCESS, k.getKlantId().toString(), k.getNaam() );
	}

}
	
