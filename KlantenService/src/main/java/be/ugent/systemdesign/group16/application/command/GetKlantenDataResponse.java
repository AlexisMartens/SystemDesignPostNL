package be.ugent.systemdesign.group16.application.command;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;
import lombok.Getter;

@Getter
public class GetKlantenDataResponse extends Response{
	private String klantenId;
	private String naam;
	public GetKlantenDataResponse(String message, ResponseStatus status, String klantenId, String naam) {
		super(message, status);
		this.klantenId = klantenId;
		this.naam = naam;
	}
	
	
}
