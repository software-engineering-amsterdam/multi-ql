package eu.bankersen.kevin.ql.ast.expr.math;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.NumberExpr;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.SymbolTableBuilder;

public class Sub extends NumberExpr {

    public Sub(Expr lhs, Expr rhs, int line) {
	super(lhs, rhs, line);
    }

    @Override
    public BigDecimal eval(SymbolTable symbolTable) throws EvaluateExeption {
	return ((BigDecimal) lhs().eval(symbolTable)).subtract((BigDecimal) rhs().eval(symbolTable));
    }
    
    @Override
    public Context checkType(Context context) {
	return context.evaluate(this);
    }

}
