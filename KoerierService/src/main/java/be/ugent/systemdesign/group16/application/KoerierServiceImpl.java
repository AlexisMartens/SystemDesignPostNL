package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.application.event.KoerierEventListener;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.KoerierRepository;
import be.ugent.systemdesign.group16.domain.NietInRondeException;
import be.ugent.systemdesign.group16.domain.Order;
import be.ugent.systemdesign.group16.domain.OrderRepository;

@Service
@Transactional
public class KoerierServiceImpl implements KoerierService {

	@Autowired
	KoerierRepository koerierRepo;

	@Autowired
	OrderRepository orderRepo;


	@Override
	public Response stuurKoerier(Integer zendingId, String typeZending, String naamVan, String postcodeVan,
			String straatVan, String plaatsVan, String landVan, String naamNaar,
			String postcodeNaar, String straatNaar, String plaatsNaar, String landNaar) {
		String postcodeRonde = postcodeVan;
		List<Koerier> koeriers = koerierRepo.findByPostcodeRonde(postcodeRonde);
		for (Koerier k : koeriers) {
			if (orderRepo.countByKoerier(k) < k.getVervoercapaciteit()) {
				try {
					Order o = new Order(zendingId, k, new Adres(naamVan,postcodeVan,straatVan,plaatsVan,landVan),
							new Adres(naamNaar,postcodeNaar,straatNaar,plaatsNaar,landNaar), LocalDate.now(), false, false);
					o.wijsKoerierToeAanOrder(k);
					orderRepo.save(o);
					return new Response(ResponseStatus.SUCCESS, "id: " + o.getOrderId());
				} catch (NietInRondeException e) {
					return new Response(ResponseStatus.FAIL, "Koerier has another postcodeRonde");
				}
			}
		}
		return new Response(ResponseStatus.FAIL, "There is no koerier who drives to this postcodeRonde");
	}

	@Override
	public Response bevestigAfleverenBuren(Integer orderId) {
		Order order = orderRepo.findOne(orderId);
		order.bevestigAfleverenBuren();
		orderRepo.save(order);
		return new Response(ResponseStatus.SUCCESS, "");
	}

	@Override
	public Response bevestigAfleveren(Integer orderId) {
		Order order = orderRepo.findOne(orderId);
		order.bevestigAfleveren();
		orderRepo.save(order);
		return new Response(ResponseStatus.SUCCESS, "");
	}

	@Override
	public Response bevestigOphalen(Integer orderId) {
		Order order = orderRepo.findOne(orderId);
		order.bevestigOphalen();
		orderRepo.save(order);
		return new Response(ResponseStatus.SUCCESS, "");
	}

}
