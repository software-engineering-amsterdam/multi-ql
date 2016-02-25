package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class VariableType extends ASTNode {

	public static final VariableType BOOLEAN = new BooleanType();
	public static final VariableType STRING = new StringType();
	public static final VariableType INTEGER = new IntegerType();

	public VariableType(ParserRuleContext context) {
		super(context);
	}

	public String getName() {
		return getClass().getSimpleName();
	}
}
