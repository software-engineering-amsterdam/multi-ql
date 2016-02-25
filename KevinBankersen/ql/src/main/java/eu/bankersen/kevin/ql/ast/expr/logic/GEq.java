package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;

public class GEq extends BooleanExpr {

    public GEq(Expr lhs, Expr rhs, int line) {
	super(lhs, rhs, line);
    }

    @Override
    public final Boolean eval(SymbolTable symbolTable) throws EvaluateExeption {
	return (Integer) lhs().eval(symbolTable) >= (Integer) rhs().eval(symbolTable);
    }

}
