package eu.bankersen.kevin.ql.ast.expr.logic;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class GT extends BooleanExpr {

    public GT(final Expr lhs, final Expr rhs, final int line) {
	super(lhs, rhs, line);
    }

    @Override
    public final Boolean eval(SymbolTable symbolTable) throws EvaluateExeption {
	return ((BigDecimal) lhs().eval(symbolTable)).compareTo((BigDecimal) rhs().eval(symbolTable)) > 0;
    }
    @Override
    public Context checkType(Context context) {
	return context.evaluate(this);
    }
    
}
