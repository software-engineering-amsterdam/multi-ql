package nl.uva.sc.ql.exceptions;

public class EvaluatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public EvaluatorException(String message){
		this.message = message;
	}
	
    @Override
    public String getMessage(){
    	return message;
    }
}
