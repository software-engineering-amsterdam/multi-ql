package eu.bankersen.kevin.ql.ast;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.symboltable.SymbolTabel;

public class Identifier extends Expr {

    private final String name;

    public Identifier(final String name) {
	this.name = name;
    }

    @Override
    public final Object result() {
	return SymbolTabel.getValue(name);
    }

    @Override
    public final void checkType() {
	
    }

    @Override
    public final Type getType() {
	return SymbolTabel.getType(name);
    }
}