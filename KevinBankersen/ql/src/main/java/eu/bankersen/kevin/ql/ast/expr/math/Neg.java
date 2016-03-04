package eu.bankersen.kevin.ql.ast.expr.math;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.NumberExpr;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class Neg extends NumberExpr {

    public Neg(final Expr expr, final int line) {
	super(expr, null, line);
    }

    @Override
    public final BigDecimal eval(SymbolTable symbolTable) throws EvaluateExeption {
	return ((BigDecimal) lhs().eval(symbolTable)).negate();
    }
    
    @Override
    public Context checkType(Context context) {
	return context.evaluate(this);
    }
}
