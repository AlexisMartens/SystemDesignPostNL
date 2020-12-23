package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Response stuurKoerier(Integer orderId, String naamAfzender, String postcodeAfzender, String straatAfzender,
			String plaatsAfzender, String landAfzender, String naamOntvanger, String postcodeOntvanger,
			String straatOntvanger, String plaatsOntvanger, String landOntvanger, boolean spoed, boolean extern,
			boolean ophalen) {
		String postcodeRonde = ophalen ? postcodeAfzender : postcodeOntvanger;
		List<Koerier> koeriers = koerierRepo.findByPostcodeRonde(postcodeRonde);
		for (Koerier k : koeriers) {
			if (orderRepo.countByKoerier(k) < k.getVervoercapaciteit()) {
				try {
					Order o = new Order(orderId, k, new Adres(naamAfzender,postcodeAfzender,straatAfzender,plaatsAfzender,landAfzender),
							new Adres(naamAfzender,postcodeOntvanger,straatOntvanger,plaatsOntvanger,landOntvanger), LocalDate.now(), spoed, extern);
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
