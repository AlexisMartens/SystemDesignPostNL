package be.ugent.systemdesign.group16.application.event;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.OrderStatus;
import lombok.Getter;

@Getter
public class StuurKoerierEvent {

	private Integer orderId;
		
	private Adres ontvanger;
	
	private Adres afzender;
	
	private boolean spoed;
	
	private boolean extern;
	
	private OrderStatus orderStatus;

	public StuurKoerierEvent(Integer orderId, Adres ontvanger, Adres afzender,
			boolean spoed, boolean extern, boolean ophalen) {
		this.orderId = orderId;
		this.ontvanger = ontvanger;
		this.afzender = afzender;
		this.spoed = spoed;
		this.extern = extern;
		this.orderStatus = orderStatus;
	}
	
	
}
