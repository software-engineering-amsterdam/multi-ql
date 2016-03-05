package eu.bankersen.kevin.ql.context;

import java.util.List;
import java.util.Map;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.context.errors.ContextBuildException;

public class TypeChecker {
    
    private final Context context;
    private final Form form;
    
    public TypeChecker(Form form) throws ContextBuildException {
	
	// The symbol-table builder will collect all the required information from the AST. 
	SymbolTableBuilder builder = new SymbolTableBuilder();
	Map<String, List<Symbol>> rawSymbolTable = form.buildSymbolTable(builder).getSymbolTable();
	
	
	this.context = form.checkType(new Context());
	this.form = form;
	
	context.analyzeDependancies();
	
	if (context.getErrors().size() > 0) {
	    throw new ContextBuildException(context.getErrors());
	}
    }
    
    public SymbolTable getSymbolTable() {
	return form.evalForm(context.getSymbolTable());
    }

}
