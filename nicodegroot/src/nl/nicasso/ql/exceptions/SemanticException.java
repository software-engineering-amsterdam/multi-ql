package nl.nicasso.ql.exceptions;

// CAN THIS GO?!
public class SemanticException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public SemanticException(String message){
		this.message = message;
	}
	
    @Override
    public String getMessage(){
    	return message;
    }
}
