package eu.bankersen.kevin.ql.ast;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.expr.Expr;

public class Identifier extends Expr {

    private final String name;
    private final int line;
    private Type type;

    public Identifier(final String name, final int line) {
	this.name = name;
	this.line = line;
    }

    @Override
    public final Object eval() {
	return super.context.getSymbol(name).getValue();
    }

    @Override
    public final void checkType() {
	if (super.context.checkID(name)) {
	    type = super.context.getSymbol(name).getType();
	} else {
	    type = Type.UNDIFINED;
	    super.context.addError("SYMANTIC_ERROR @Line " + line 
		    			+ ": \"" + name + "\" does not exist!");
	}
    }

    @Override
    public final Type getType() {
	return type;
    }
}
