package eu.bankersen.kevin.ql.typechecker.errors;

import eu.bankersen.kevin.ql.typechecker.symboltable.Symbol;

public class AllreadyDeclaredError extends TypeCheckError {
    
    public AllreadyDeclaredError(int i, Symbol symbol1, Symbol symbol2) {
	super(i, String.format("The question \"%s\" is already declared with type %s instead of %s!",
		symbol1.getName(), symbol1.getType(), symbol2.getType()));
    }
    

}
