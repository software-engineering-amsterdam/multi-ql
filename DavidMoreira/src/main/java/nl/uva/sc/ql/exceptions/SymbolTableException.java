package nl.uva.sc.ql.exceptions;

public class SymbolTableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public SymbolTableException(String message){
		this.message = message;
	}
	
    @Override
    public String getMessage(){
    	return message;
    }
}
