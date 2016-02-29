package eu.bankersen.kevin.ql.context;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.context.errors.ContextBuildException;

public class TypeChecker {
    
    private final Context context;
    private final Form form;
    
    public TypeChecker(Form form) throws ContextBuildException {
	this.context = form.checkType();
	this.form = form;
	
	if (context.getErrors().size() > 0) {
	    throw new ContextBuildException(context.getErrors());
	}
    }
    
    public SymbolTable getSymbolTable() {
	return form.evalForm(context.getSymbolTable());
    }

}
