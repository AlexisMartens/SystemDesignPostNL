package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
