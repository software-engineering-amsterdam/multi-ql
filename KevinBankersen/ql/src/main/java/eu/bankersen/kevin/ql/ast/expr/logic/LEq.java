package eu.bankersen.kevin.ql.ast.expr.logic;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class LEq extends BooleanExpr {

    public LEq(Expr lhs, Expr rhs, int line) {
	super(lhs, rhs, line);
    }

    @Override
    public Boolean evalExpr(SymbolTable symbolTable) throws EvaluateExeption {
	return ((BigDecimal) lhs().evalExpr(symbolTable)).compareTo((BigDecimal) rhs().evalExpr(symbolTable)) <= 0;
    }

    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }

}
