package nl.uva.sc.ql.exceptions;

public class InterpreterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InterpreterException(String message){
		this.message = message;
	}
	
    @Override
    public String getMessage(){
    	return message;
    }
}
