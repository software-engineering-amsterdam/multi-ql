package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.Type;
import eu.bankersen.kevin.ql.ast.Variable;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class Question extends Statement  {

    private final Variable variable;
    private final String text;
    private Object value;

    public Question(Variable variable, String text) {
	this.variable = variable;
	this.text = text.substring(1, text.length()-1);
    }

    public String getText() {
	return text;
    }

    public Context checkType(Context context) {
	return variable.checkType(context, text);
    }

    public Type getType() {
	return variable.getType();
    }
    
    public String getName() {
	return variable.getName();
    }
    
    public Object getValue() {
	return value;
    }
    
    public SymbolTable visible(SymbolTable symbolTable, Boolean visible) {
	symbolTable.setVisibility(this.getName(), visible);
	return symbolTable;
    }
    
    @Override
    public Context visible(Context context, Boolean visible) {
	context.setVisibility(this.getName(), visible);
	return context;
    }
    
    public SymbolTable evalStatement(SymbolTable symbolTable) {
	return variable.eval(symbolTable);
    }
    
    @Override
    public String toString() {
	return text + "\n" + variable.toString() + "\n\n";
    }
}
