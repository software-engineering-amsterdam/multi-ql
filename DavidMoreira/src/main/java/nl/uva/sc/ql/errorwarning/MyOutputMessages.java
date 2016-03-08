package nl.uva.sc.ql.errorwarning;

public abstract class MyOutputMessages {

	private String message;
	
	public MyOutputMessages(String message){
		this.message = message;
	}
	
    public String getMessage(){
    	return message;
    }
    
    @Override
    public String toString(){
    	return getMessage();
    }
}
