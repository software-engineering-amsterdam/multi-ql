package nl.uva.sc.ql.exceptions;

public class CreateASTTreeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CreateASTTreeException(String message){
		this.message = message;
	}
	
    @Override
    public String getMessage(){
    	return message;
    }
}
