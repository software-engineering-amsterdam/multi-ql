package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class Not extends BooleanExpr {

    public Not(Expr expr, int line) {
	super(expr, null, line);
    }

    @Override
    public final Boolean evalExpr(SymbolTable symbolTable) throws EvaluateExeption {
	return !(Boolean) lhs().evalExpr(symbolTable);
    }
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }
}
