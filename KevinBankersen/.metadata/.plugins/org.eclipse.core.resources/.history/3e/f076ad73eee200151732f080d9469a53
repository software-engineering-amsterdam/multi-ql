package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.type.BooleanType;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.SymbolTableBuilder;

public abstract class BooleanExpr extends Expr {

    public BooleanExpr(Expr lhs, Expr rhs, int line) {
	super(new BooleanType(), lhs, rhs, line);
    }
    
    public abstract Boolean eval(SymbolTable symbolTable) throws EvaluateExeption;
    

    
}
