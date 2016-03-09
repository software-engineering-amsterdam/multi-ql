package eu.bankersen.kevin.ql.typechecker.errors;

public abstract class TypeCheckError {
    
    private final String message;
    private final int line;
    
    public TypeCheckError(int line, String message) {
	this.line = line;
	this.message = message;
    }
    
    public String toString() {
	return line == 0 ? message : String.format("Line %s: %s", line, message);
    }
    
}
