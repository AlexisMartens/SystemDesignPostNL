package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BevestigAfleverenZendingEvent extends DomainEvent {

	private Integer orderId;
	
	private Adres ontvanger;
	
	private Adres afzender;
	
	private boolean spoed;
	
	private boolean extern;
	
	private OrderStatus orderStatus;


}
