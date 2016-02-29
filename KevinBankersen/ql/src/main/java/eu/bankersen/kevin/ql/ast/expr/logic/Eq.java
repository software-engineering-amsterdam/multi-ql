package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;

public class Eq extends BooleanExpr {

    public Eq(Expr lhs, Expr rhs, int line) {
	super(lhs, rhs, line);
    }

    @Override
    public Boolean eval(SymbolTable symbolTable) throws EvaluateExeption  {
	return lhs().eval(symbolTable).equals(rhs().eval(symbolTable));
    }

}
