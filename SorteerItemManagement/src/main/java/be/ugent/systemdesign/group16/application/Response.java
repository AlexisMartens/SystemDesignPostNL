package be.ugent.systemdesign.group16.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
	
	private ResponseStatus status;
	private String message;
}
