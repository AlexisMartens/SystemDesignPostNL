package be.ugent.systemdesign.group16.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.Order;
import be.ugent.systemdesign.group16.domain.OrderRepository;
import be.ugent.systemdesign.group16.domain.OrderStatus;

public class OrderRepositoryImpl implements OrderRepository{

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
	}
	
/*
 * 	private Integer orderId;
	
	private Adres ontvanger;
	
	private Adres afzender;
	
	private boolean ophalen;
	
	private LocalDate aanmaakDatum;
	
	private boolean spoed;
	
	private boolean extern;
	
	private OrderStatus orderStatus;*/
	
	
	
	/*OrderDataModel(Integer orderId, KoerierDataModel koerierDataModel, String naamAfzender,
			String postcodeAfzender, String straatAfzender, String plaatsAfzender, String landAfzender,
			String naamOntvanger, String postcodeOntvanger, String straatOntvanger, String plaatsOntvanger,
			String landOntvanger, LocalDate aanmaakDatum, boolean spoed, boolean extern, String orderType,
			String orderStatus)
			*/
 
	private OrderDataModel mapToOrderDataModel(Order o) {
		return new OrderDataModel(o.getOrderId(),mapToKoerierDataModel(o.getKoerier()),o.getAfzender().getNaam(), o.getAfzender().getPostcode(), o.getAfzender().getStraat(), o.getAfzender().getPlaats(), o.getAfzender().getLand(), o.getOntvanger().getNaam(), o.getOntvanger().getPostcode(), o.getOntvanger().getStraat(), o.getOntvanger().getPlaats(), o.getOntvanger().getLand(), o.getAanmaakDatum(), o.isSpoed(),o.isExtern(),o.getOrderStatus().toString());
	}
	
	private Order mapToOrder(OrderDataModel orderDM) {
		Order k = Order.builder()
						.orderId(orderDM.getOrderId())
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