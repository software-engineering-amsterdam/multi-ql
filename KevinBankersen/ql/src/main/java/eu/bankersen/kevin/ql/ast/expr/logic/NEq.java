package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;

public class NEq extends BooleanExpr {

    public NEq(Expr lhs, Expr rhs, int line) {
	super(lhs, rhs, line);
    }

    @Override
    public final Boolean eval(SymbolTable symbolTable) throws EvaluateExeption {
	return !lhs().eval(symbolTable).equals(rhs().eval(symbolTable));
    }

}
