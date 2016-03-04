package nl.uva.sc.ql.errorwarning;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
	
	private List<MyError> errors;
	private List<MyWarning> warnings;
	
	public ErrorHandler() {
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
