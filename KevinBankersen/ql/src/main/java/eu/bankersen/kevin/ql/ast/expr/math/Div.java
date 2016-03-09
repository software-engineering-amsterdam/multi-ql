package eu.bankersen.kevin.ql.ast.expr.math;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.NumberExpr;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class Div extends NumberExpr {

    public Div(final Expr lhs, final Expr rhs,  final int line) {
	super(lhs, rhs, line);
    }

    @Override
    public final BigDecimal evalExpr(SymbolTable symbolTable) throws EvaluateExeption {
	return ((BigDecimal) lhs().evalExpr(symbolTable)).divide((BigDecimal) rhs().evalExpr(symbolTable), 2 , BigDecimal.ROUND_CEILING);
    }
    
    @Override
    public  <T>  void accept(BasicVisitor v, T context) {
	v.visit(this);
    }


}
