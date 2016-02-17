package org.uva.ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class VariableType extends ASTNode {

	public VariableType(ParserRuleContext context) {
		super(context);
	}
}
