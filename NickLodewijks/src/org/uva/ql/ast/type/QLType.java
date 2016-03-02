package org.uva.ql.ast.type;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;

public abstract class QLType extends ASTNode {

	public static final QLType BOOLEAN = new QLBooleanType();
	public static final QLType STRING = new QLStringType();
	public static final QLType INTEGER = new QLIntegerType();

	public QLType(ParserRuleContext context) {
		super(context);
	}

	public String getName() {
		return getClass().getSimpleName();
	}

	public abstract <T> T accept(QLTypeVisitor<T> visitor);
}
