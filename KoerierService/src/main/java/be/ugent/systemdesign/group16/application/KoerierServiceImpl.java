package be.ugent.systemdesign.group16.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.KoerierRepository;
import be.ugent.systemdesign.group16.domain.NietInRondeException;
import be.ugent.systemdesign.group16.domain.Order;
import be.ugent.systemdesign.group16.domain.OrderRepository;
import be.ugent.systemdesign.group16.domain.OrderStatus;

@Service
@Transactional
public class KoerierServiceImpl implements KoerierService{

	@Autowired
	KoerierRepository koerierRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public Response stuurKoerier(Integer orderId) {
		try {
			//NIEUWE ORDER MAKEN EN SAVEN NAAR DATABANK
			// VOOR DEZE ORDER: WIJS TAAK TOE AAN KOERIER
			//WEER SAVEN
			
		}
			 //zoek een koerier waarvan postcodeRonde matcht && vervoercapaciteit < orders.size()
			Order order = orderRepo.findOne(orderId);
			
			String postcodeRonde;
			if(order.getOrderStatus() == OrderStatus.OP_TE_HALEN) {
				postcodeRonde = order.getAfzender().getPostcode();
			}
			else {
				postcodeRonde = order.getOntvanger().getPostcode();
			}
			List<Koerier> koeriers = koerierRepo.findByPostcodeRonde(postcodeRonde);
			for(Koerier k: koeriers) {
				if(k.getOrders().size()<k.getVervoercapaciteit()) {
					try{
						k.VoegOrderToe(order);
						koerierRepo.save(k);  //?
						return new Response(ResponseStatus.SUCCESS,"id: "+order.getOrderId());
					} catch(NietInRondeException e) {
						return new Response(ResponseStatus.FAIL,"Koerier has another postcodeRonde");
					}
				}
			}
			return new Response(ResponseStatus.FAIL,"There is no koerier who drives to this postcodeRonde");
	}

	@Override
	public Response bevestigAfleverenBuren(Integer orderId) {
		Order order = orderRepo.findOne(orderId);
		order.bevestigAfleverenBuren();
		orderRepo.save(order);
		return null;
	}

	@Override
	public Response bevestigAfleveren(Integer orderId) {
		Order order = orderRepo.findOne(orderId);
		order.setOrderStatus(OrderStatus.AFGELEVERD);
		return null;
	}

	@Override
	public Response bevestigOphalen(Integer orderId) {
		Order order = orderRepo.findOne(orderId);
		order.setOrderStatus(OrderStatus.OPGEHAALD);
		return null;
	}

}
