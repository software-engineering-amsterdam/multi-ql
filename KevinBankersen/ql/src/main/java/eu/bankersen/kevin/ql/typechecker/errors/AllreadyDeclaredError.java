package eu.bankersen.kevin.ql.typechecker.errors;

public class AllreadyDeclaredError extends TypeCheckError {

    public AllreadyDeclaredError(int i, String name) {
	super(i, String.format("The question \"%s\" is declared multiple times!", name));
    }

}
