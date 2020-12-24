package be.ugent.systemdesign.group16.domain.seedwork;

import java.time.LocalDateTime;

import lombok.Getter;

public class DomainEvent {

	@Getter
	private LocalDateTime createdAt;
	
	public DomainEvent() {
		this.createdAt=LocalDateTime.now();
	}
}
