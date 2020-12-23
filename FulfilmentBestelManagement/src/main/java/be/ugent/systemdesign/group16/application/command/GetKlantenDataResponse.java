package be.ugent.systemdesign.group16.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetKlantenDataResponse {
	private String klantenId;
	private String naam;
}
