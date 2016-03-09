package org.uva.ql.ast.type;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTSourceInfo;

public abstract class QLType extends ASTNode {

	public static final QLType BOOLEAN = new QLBooleanType();
	public static final QLType STRING = new QLStringType();
	public static final QLType INTEGER = new QLIntegerType();

	public QLType(ASTSourceInfo context) {
		super(context);
	}

	public abstract <T, U> T accept(QLTypeVisitor<T, U> visitor, U context);
}
