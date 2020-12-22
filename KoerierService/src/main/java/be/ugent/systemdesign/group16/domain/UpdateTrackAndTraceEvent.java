package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class UpdateTrackAndTraceEvent extends DomainEvent {

	private Integer orderId;
	
	private OrderStatus orderStatus;
	
	public UpdateTrackAndTraceEvent(Integer orderId, OrderStatus orderStatus) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
	}
}
