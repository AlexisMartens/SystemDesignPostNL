package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Magazijn{ //extends AggregateRoot {
	//TODO: welke attributen??
	private Integer magazijnId;


	private Adres locatie;


	
	/*public Magazijn(Magazijn _m) {
		
	}*/


	// TODO: verwerk methode(s)?
	
}
