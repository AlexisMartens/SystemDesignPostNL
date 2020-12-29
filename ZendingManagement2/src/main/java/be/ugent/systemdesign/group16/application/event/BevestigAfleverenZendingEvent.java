package be.ugent.systemdesign.group16.application.event;

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
}
