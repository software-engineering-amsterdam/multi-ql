package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.type.BooleanType;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public abstract class BooleanExpr extends Expr {

    public BooleanExpr(Expr lhs, Expr rhs, int line) {
	super(new BooleanType(), lhs, rhs, line);
    }
    
    public abstract Boolean evalExpr(SymbolTable symbolTable) throws EvaluateExeption;
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }
    
}
