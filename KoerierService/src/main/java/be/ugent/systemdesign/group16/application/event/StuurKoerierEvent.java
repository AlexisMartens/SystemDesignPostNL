package be.ugent.systemdesign.group16.application.event;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.OrderStatus;
import lombok.Getter;

@Getter
public class StuurKoerierEvent {

	private Integer orderId;
	
	private Koerier koerier;
	
	private Adres ontvanger;
	
	private Adres afzender;
	
	private LocalDate aanmaakDatum;
	
	private boolean spoed;
	
	private boolean extern;
	
	private OrderStatus orderStatus;

	public StuurKoerierEvent(Integer orderId, Koerier koerier, Adres ontvanger, Adres afzender, LocalDate aanmaakDatum,
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
	
	
}
