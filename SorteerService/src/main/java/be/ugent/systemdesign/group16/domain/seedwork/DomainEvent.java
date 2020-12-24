package be.ugent.systemdesign.group16.domain.seedwork;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainEvent {

	private LocalDateTime createdAt;
	
	public DomainEvent() {
		this.createdAt=LocalDateTime.now();
	}
}
