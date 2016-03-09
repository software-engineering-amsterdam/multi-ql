package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.ast.type.value.QLObject;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class Literal extends Expr {

    private final Object value;
    
    private QLObject value2;
    
    public Literal(Object value, Type type, int line) {
	super(type, null, null, line);
	this.value = value;
    }
    
    @Override
    public Object evalExpr(SymbolTable symbolTable) {
	return value; 
    }
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }
}
