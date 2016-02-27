package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.IntegerExpr;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class Add extends IntegerExpr {

    public Add(Expr lhs, Expr rhs, int line) {
	super(lhs, rhs, line);
    }

    @Override
    public Integer eval(SymbolTable symbolTable) throws EvaluateExeption {
	return (Integer) lhs().eval(symbolTable) + (Integer) rhs().eval(symbolTable);
    }

}
