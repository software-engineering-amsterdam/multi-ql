package eu.bankersen.kevin.ql.context.errors;

public class NotDeclaredError extends TypeCheckingError {

    public NotDeclaredError(int line, String name) {
	super(line, "\"" + name + "\" not declared!");
    }

}
