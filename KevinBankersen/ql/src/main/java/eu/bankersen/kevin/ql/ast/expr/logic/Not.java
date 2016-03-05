package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class Not extends BooleanExpr {

    public Not(Expr expr, int line) {
	super(expr, null, line);
    }
    
    @Override
    public Context checkType(Context context) {
	return context.evaluate(this);
    }

    @Override
    public final Boolean eval(SymbolTable symbolTable) throws EvaluateExeption {
	return !(Boolean) lhs().eval(symbolTable);
    }
}
