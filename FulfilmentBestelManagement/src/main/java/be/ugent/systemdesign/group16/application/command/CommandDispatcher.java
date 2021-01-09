package be.ugent.systemdesign.group16.application.command;

public interface CommandDispatcher {
	
	void sendGetKlantenDataCommand(GetKlantenDataCommand command);
	
}
