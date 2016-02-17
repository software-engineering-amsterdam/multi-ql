package nl.uva.sc.ql.exceptions;

public class TypecheckerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public TypecheckerException(String message){
		this.message = message;
	}
	
    @Override
    public String getMessage(){
    	return message;
    }
}
