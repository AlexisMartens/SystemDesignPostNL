package be.ugent.systemdesign.group16.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {
	final public String message;
	final public ZendingStatus status;
	Response(ZendingStatus status, String message){ this.status = status; this.message = message;}
}
