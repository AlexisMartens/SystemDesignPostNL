package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.ugent.systemdesign.group16.application.event.BestellingEventListener;
import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
public class FulfilmentKlant extends AggregateRoot {
	
	private Integer klantId;
	
	private String naam;
		
	
	
	public void maakFulfilmentKlant() {
		addDomainEvent(new NieuweTrackAndTraceDomainEvent(bestellingId.toString(), status.name()));
		addDomainEvent(new PacketDomainEvent(bestellingId, typeBestelling, ontvanger.getNaam(), ontvanger.getPostcode(),ontvanger.getStraat(),ontvanger.getPlaats(), ontvanger.getLand(), afzender.getNaam(), afzender.getPostcode(), afzender.getStraat(), afzender.getPlaats(), afzender.getLand(), ophalen, spoed));
		status=BestellingStatus.VERWERKT;
	}
	
	public void stopFulfilmentKlant() {
		repo.delete
	}
}
