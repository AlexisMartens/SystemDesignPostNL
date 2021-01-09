package be.ugent.systemdesign.group16.application.command;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ResponseStatus;
import lombok.Getter;

@Getter
public class GetKlantenDataResponse extends Response{
	private String klantId;
	private String naam;
	public GetKlantenDataResponse(String message, ResponseStatus status, String klantId, String naam) {
		super(message, status);
		this.klantId = klantId;
		this.naam = naam;
	}
	
	
}
