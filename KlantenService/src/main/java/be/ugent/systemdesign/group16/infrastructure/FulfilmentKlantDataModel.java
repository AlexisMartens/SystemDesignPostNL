package be.ugent.systemdesign.group16.infrastructure;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FulfilmentKlantDataModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer klantId;
	
	private String naam;
}
