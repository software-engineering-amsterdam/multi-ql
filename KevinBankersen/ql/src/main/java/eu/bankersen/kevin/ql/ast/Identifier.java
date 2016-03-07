package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.ast.type.UndifinedType;
import eu.bankersen.kevin.ql.ast.type.value.QLObject;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;


public class Identifier extends Expr {

    private final String name;
    private QLObject value;

    public Identifier(String name, int line) {
	super(new UndifinedType(), null, null, line);
	this.name = name;
    }
    
    public String name() {
	return name;
    }

    @Override
    public Object evalExpr(SymbolTable symbolTable) throws EvaluateExeption {

	Object value = symbolTable.getSymbol(name).getValue();
	
	if (value != null) {
	    return value;   
	} else {
	    throw new EvaluateExeption();
	} 
    }
    
    @Override
    public Type getType(SymbolTable symbolTable) {
	return symbolTable.getSymbol(name).getType();
    }
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }

}
