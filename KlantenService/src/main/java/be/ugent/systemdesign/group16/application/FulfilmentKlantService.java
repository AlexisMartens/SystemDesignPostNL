package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.application.command.GetKlantenDataResponse;
import be.ugent.systemdesign.group16.domain.FulfilmentKlant;

public interface FulfilmentKlantService {
	Response maakFulfilmentKlant(FulfilmentKlant k);
	Response stopFulfilmentKlant(Integer id);
	GetKlantenDataResponse getKlantenData(String id);
}
