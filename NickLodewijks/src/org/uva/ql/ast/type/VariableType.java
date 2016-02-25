package org.uva.ql.ast.type;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;

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
