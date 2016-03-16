package nl.uva.sc.ql.messages;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sc.ql.messages.errors.MyError;
import nl.uva.sc.ql.messages.warnings.MyWarning;

public class MessagesHandler {
	
	private List<MyError> errors;
	private List<MyWarning> warnings;
	
	public MessagesHandler() {
		this.errors = new ArrayList<MyError>();
		this.warnings = new ArrayList<MyWarning>();
	}
	
	public void addError(MyError error){
		this.errors.add(error);
	}
	
	public boolean asError(){
		return !errors.isEmpty();
	}
	
	public void addWarning(MyWarning warning){
		this.warnings.add(warning);
	}
	
	public boolean asWarning(){
		return !warnings.isEmpty();
	}
	
	@Override
	public String toString(){
		String allErrors = "";
		String allWarnings = "";
		
		for(MyError s : errors){
			allErrors += s.toString()+"\n";
		}
		
		for(MyWarning s : warnings){
			allWarnings += s.toString()+"\n";
		}
		
		return "Errors founded:\n"+allErrors+"Warnings founded:\n"+allWarnings;
	}
}
