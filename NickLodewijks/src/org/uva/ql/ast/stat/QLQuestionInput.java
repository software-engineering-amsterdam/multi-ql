package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.type.QLType;

public class QLQuestionInput extends QLQuestion {

	public QLQuestionInput(ParserRuleContext context, QLType type, String id, String label) {
		super(context, type, id, label);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
