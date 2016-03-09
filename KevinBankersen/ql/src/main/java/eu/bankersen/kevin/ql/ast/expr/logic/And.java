package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class And extends BooleanExpr {

    public And(Expr lhs, Expr rhs, int line) {
	super(lhs, rhs, line);
    }

    @Override
    public Boolean evalExpr(SymbolTable symbolTable) throws EvaluateExeption {
	return (Boolean) lhs().evalExpr(symbolTable) && (Boolean) rhs().evalExpr(symbolTable);
    }
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }
}
