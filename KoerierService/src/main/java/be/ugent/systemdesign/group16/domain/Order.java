package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class Order {
	
	private Integer orderId;
	
	private Koerier koerier;
	
	private Adres ontvanger;
	
	private Adres afzender;
	
	private LocalDate aanmaakDatum;
	
	private boolean spoed;
	
	private boolean extern;
	
	private OrderStatus orderStatus;

	public Order(Integer orderId, Koerier koerier, Adres ontvanger, Adres afzender, LocalDate aanmaakDatum,
			boolean spoed, boolean extern, OrderStatus orderStatus) {
		
		this.orderId = orderId;
		this.koerier = koerier;
		this.ontvanger = ontvanger;
		this.afzender = afzender;
		this.aanmaakDatum = aanmaakDatum;
		this.spoed = spoed;
		this.extern = extern;
		this.orderStatus = orderStatus;
	}

	
	//moet allemaal in de Order klasse
	//zal de status van een Order aanpassen en daarvoor nodige events uitsturen

	public void BevestigAfleverenBuren() {
		// TODO
		this.orderStatus = OrderStatus.AFGELEVERD_BIJ_BUREN;
		// Events sturen naar track&trace en event sturen naar ZendingManagement
		
		
		// zal binnenkomen via de restcontroller als systeemoperatie
		// zal je hier status aanpassen en events sturen naar track&trace en event sturen nr zendingmanagement
	}
	
	public void BevestigOphalen() {
		//TODO
		this.orderStatus = OrderStatus.OPGEHAALD;
		// Events sturen naar track&trace en event sturen naar ZendingManagement


	}
	
	public void BevestigAfleveren() {
		//TODO
		this.orderStatus = OrderStatus.AFGELEVERD;
		// Events sturen naar track&trace en event sturen naar ZendingManagement


	}
	
	//in db alle kioereris opvragen die gleinkd zijn aan order
		public void WijsKoerierToeAanOrder(Koerier koerier){
			setKoerier(koerier);
			setOrderStatus(OrderStatus.TOEGEWEZEN);
//+status veranderd naar toegewezne aan koerier;
			
			/*if(order.getAfzender().getPostcode().equals(this.postcodeRonde) || order.getOntvanger().getPostcode().equals(this.postcodeRonde)) {
					orders.add(order);
			}
			else {
				throw new NietInRondeException();
			}*/
		}



	
}
