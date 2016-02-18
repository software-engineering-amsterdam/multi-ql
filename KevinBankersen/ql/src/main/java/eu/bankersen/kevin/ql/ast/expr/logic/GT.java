package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;

public class GT extends BooleanExpr {

    public GT(final Expr lhs, final Expr rhs, final int line) {
	super.lhs = lhs;
	super.rhs = rhs;
	super.line = line;
    }

    @Override
    public final Boolean eval() {
	Log.error("Made it here");
	
	return (Integer) lhs.eval() > (Integer) rhs.eval();
    }
}
