package eu.bankersen.kevin.ql.context.errors;

public class OutOfScopeError extends TypeCheckingError {

    public OutOfScopeError(int line, String name) {
	super(line, "\"" + name + "\" out of scope!");
    }
    
    

}
