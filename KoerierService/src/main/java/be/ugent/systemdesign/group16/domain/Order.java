package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	//@Id
	private Integer orderId;
	
	private Koerier koerier;
	
	private Adres ontvanger;
	
	private Adres afzender;
	
	private LocalDate aanmaakDatum;
	
	private boolean spoed;
	
	private boolean extern;
	
	private OrderStatus orderStatus;

	public Order(Integer orderId, Koerier koerier, Adres ontvanger, Adres afzender, LocalDate aanmaakDatum,
			boolean spoed, boolean extern) {
		this.orderId = orderId;
		this.koerier = koerier;
		this.ontvanger = ontvanger;
		this.afzender = afzender;
		this.aanmaakDatum = aanmaakDatum;
		this.spoed = spoed;
		this.extern = extern;
	}

	
	//moet allemaal in de Order klasse
	//zal de status van een Order aanpassen en daarvoor nodige events uitsturen

	public void bevestigAfleverenBuren() {
		// TODO
		setOrderStatus(OrderStatus.AFGELEVERD_BIJ_BUREN);
		// Events sturen naar track&trace en event sturen naar ZendingManagement
		
		
		// zal binnenkomen via de restcontroller als systeemoperatie
		// zal je hier status aanpassen en events sturen naar track&trace en event sturen nr zendingmanagement
	}
	
	public void bevestigOphalen() {
		//TODO
		this.orderStatus = OrderStatus.OPGEHAALD;
		// Events sturen naar track&trace en event sturen naar ZendingManagement


	}
	
	public void bevestigAfleveren() {
		//TODO
		this.orderStatus = OrderStatus.AFGELEVERD;
		// Events sturen naar track&trace en event sturen naar ZendingManagement


	}
	
	public void wijsKoerierToeAanOrder(Koerier koerier){
		setKoerier(koerier);
		setOrderStatus(OrderStatus.TOEGEWEZEN_AAN_KOERIER);
	}



	
}
