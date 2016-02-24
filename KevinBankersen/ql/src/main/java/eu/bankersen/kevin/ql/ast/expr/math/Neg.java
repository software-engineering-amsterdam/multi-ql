package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.IntegerExpr;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class Neg extends IntegerExpr {

    public Neg(final Expr expr, final int line) {
	super(expr, null, line);
    }

    @Override
    public final Integer eval(SymbolTable symbolTable) throws EvaluateExeption {
	return -(Integer) lhs().eval(symbolTable);
    }

}
