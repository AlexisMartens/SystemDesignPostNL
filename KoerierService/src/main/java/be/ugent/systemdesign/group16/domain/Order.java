package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import javax.persistence.Id;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
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
public class Order extends AggregateRoot{
	
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

	public void bevestigAfleverenBuren() {
		setOrderStatus(OrderStatus.AFGELEVERD_BIJ_BUREN);
		addDomainEvent(new UpdateTrackAndTraceEvent(getOrderId(),getAfzender().getNaam(), getAfzender().getPostcode(), getAfzender().getStraat(), getAfzender().getPlaats(), getAfzender().getLand(), getOrderStatus().toString()));
		addDomainEvent(new BevestigAfleverenZendingEvent(getOrderId()));
	}
	
	public void bevestigOphalen() {
		setOrderStatus(OrderStatus.OPGEHAALD);
		addDomainEvent(new UpdateTrackAndTraceEvent(getOrderId(),getAfzender().getNaam(), getAfzender().getPostcode(), getAfzender().getStraat(), getAfzender().getPlaats(), getAfzender().getLand(), getOrderStatus().toString()));
		addDomainEvent(new BevestigOphalenZendingEvent(getOrderId()));
	}
	
	public void bevestigAfleveren() {
		setOrderStatus(OrderStatus.AFGELEVERD);
		addDomainEvent(new UpdateTrackAndTraceEvent(getOrderId(),getAfzender().getNaam(), getAfzender().getPostcode(), getAfzender().getStraat(), getAfzender().getPlaats(), getAfzender().getLand(), getOrderStatus().toString()));
		addDomainEvent(new BevestigAfleverenZendingEvent(getOrderId()));
	}
	public void wijsKoerierToeAanOrder(Koerier koerier){
		setKoerier(koerier);
		setOrderStatus(OrderStatus.TOEGEWEZEN_AAN_KOERIER);
		addDomainEvent(new UpdateTrackAndTraceEvent(getOrderId(),getAfzender().getNaam(), getAfzender().getPostcode(), getAfzender().getStraat(), getAfzender().getPlaats(), getAfzender().getLand(), getOrderStatus().toString()));
	}



	
}
