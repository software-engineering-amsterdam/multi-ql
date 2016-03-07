package eu.bankersen.kevin.ql.ast.expr;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.type.IntType;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public abstract class NumberExpr extends Expr {

    public NumberExpr(Expr lhs, Expr rhs, int line) {
	super(new IntType(), lhs, rhs, line);
    }

    public abstract BigDecimal evalExpr(SymbolTable symbolTable) throws EvaluateExeption;
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }

}
