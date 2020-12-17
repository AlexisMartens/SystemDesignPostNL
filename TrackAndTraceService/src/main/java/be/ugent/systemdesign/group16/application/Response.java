package be.ugent.systemdesign.group16.application;

public class Response {
	final public String message;
	final public ResponseStatus status;
	Response(ResponseStatus status, String message){ this.status = status; this.message = message;}
}
