package nl.uva.sc.ql.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ExceptionHandling {

	private static ExceptionHandling exceptionHandling = null;
	
	private List<RuntimeException> errors;
	private List<RuntimeException> warnings;
	
	private ExceptionHandling() {
		this.errors = new ArrayList<RuntimeException>();
		this.warnings = new ArrayList<RuntimeException>();
	}
	
	public static ExceptionHandling getInstance() {
		if (exceptionHandling == null) { 
			exceptionHandling = new ExceptionHandling(); 
		}
		return exceptionHandling;
	}
	
	public void addError(RuntimeException error){
		this.errors.add(error);
	}
	
	public boolean asError(){
		return !errors.isEmpty();
	}
	
	public void addWarning(RuntimeException warning){
		this.warnings.add(warning);
	}
	
	public boolean asWarning(){
		return !warnings.isEmpty();
	}
	
	@Override
	public String toString(){
		String allErrors = "";
		String allWarnings = "";
		
		for(RuntimeException s : errors){
			allErrors += s.toString()+"\n";
		}
		
		for(RuntimeException s : warnings){
			allWarnings += s.toString()+"\n";
		}
		
		return "Errors founded:\n"+allErrors+"Warnings founded:\n"+allWarnings;
	}
}
