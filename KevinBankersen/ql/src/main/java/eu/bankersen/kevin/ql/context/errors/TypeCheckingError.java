package eu.bankersen.kevin.ql.context.errors;

public abstract class TypeCheckingError {
    
    private final String message;
    private final int line;
    
    public TypeCheckingError(int line, String message) {
	this.line = line;
	this.message = message;
    }
    
    public String toString() {
	return "Line " + line + "; " + message;
    }
    
}
