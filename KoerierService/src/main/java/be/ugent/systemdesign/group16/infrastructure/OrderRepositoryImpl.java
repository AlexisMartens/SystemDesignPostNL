package be.ugent.systemdesign.group16.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.group16.application.event.KoerierEventListener;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.Order;
import be.ugent.systemdesign.group16.domain.OrderRepository;
import be.ugent.systemdesign.group16.domain.OrderStatus;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	OrderDataModelJpaRepository orderDMJPARepo;
	
	@Override
	public Order findOne(Integer id) {
		OrderDataModel dataModel = orderDMJPARepo.findById(id).orElseThrow(OrderNotFoundException::new);
		return mapToOrder(dataModel);	
	}

	@Override
	public void save(Order order) {
		OrderDataModel dataModel = mapToOrderDataModel(order);		
		orderDMJPARepo.save(dataModel);		
		
		order.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		order.clearDomainEvents();
	}
	
	@Override
	public Integer countByKoerier(Koerier koerier) {
		return orderDMJPARepo.countByKoerierDataModel(mapToKoerierDataModel(koerier));
	}
 
	private OrderDataModel mapToOrderDataModel(Order o) {
		return new OrderDataModel(o.getOrderId(),mapToKoerierDataModel(o.getKoerier()),o.getAfzender().getNaam(), o.getAfzender().getPostcode(), o.getAfzender().getStraat(), o.getAfzender().getPlaats(), o.getAfzender().getLand(), o.getOntvanger().getNaam(), o.getOntvanger().getPostcode(), o.getOntvanger().getStraat(), o.getOntvanger().getPlaats(), o.getOntvanger().getLand(), o.getAanmaakDatum(), o.isSpoed(),o.isExtern(),o.getOrderStatus().toString());
	}
	
	private Order mapToOrder(OrderDataModel orderDM) {
		Order k = Order.builder()
						.orderId(orderDM.getOrderId())
						.koerier(mapToKoerier(orderDM.getKoerierDataModel()))
						.ontvanger(
								Adres.builder()
								.naam(orderDM.getNaamOntvanger())
								.plaats(orderDM.getPlaatsOntvanger())
								.postcode(orderDM.getPostcodeOntvanger())
								.straat(orderDM.getStraatOntvanger())
								.land(orderDM.getLandOntvanger())
								.build()
								)
						.afzender(
								Adres.builder()
								.naam(orderDM.getNaamAfzender())
								.plaats(orderDM.getPlaatsAfzender())
								.postcode(orderDM.getPostcodeAfzender())
								.straat(orderDM.getStraatAfzender())
								.land(orderDM.getLandAfzender())
								.build()
								)
						.aanmaakDatum(orderDM.getAanmaakDatum())
						.spoed(orderDM.isSpoed())
						.extern(orderDM.isExtern())
						.orderStatus(OrderStatus.valueOf(orderDM.getOrderStatus()))
						.build();
		return k;
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
