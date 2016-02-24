package nl.uva.sc.ql.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandling {

	private static ErrorHandling errorHandling = null;
	private List<String> errors;
	
	private ErrorHandling() {
		this.errors = new ArrayList<String>();
	}
	
	public static ErrorHandling getInstance() {
		if (errorHandling == null) { 
			errorHandling = new ErrorHandling(); 
		}
		return errorHandling;
	}
	
	public void addError(String error){
		this.errors.add(error);
	}
	
	public boolean asError(){
		return !errors.isEmpty();
	}
	
	@Override
	public String toString(){
		String allErrors = "";
		
		for(String s : errors){
			allErrors += s+"\n";
		}
		return allErrors;
	}	
}
