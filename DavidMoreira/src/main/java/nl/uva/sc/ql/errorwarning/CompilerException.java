package nl.uva.sc.ql.errorwarning;

public class CompilerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CompilerException(String message){
		this.message = message;
	}
	
    @Override
    public String getMessage(){
    	return message;
    }
}
