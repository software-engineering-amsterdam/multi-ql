package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.type.IntegerType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class IntegerLiteral extends Literal<Integer> {

	public IntegerLiteral(Integer value) {
		super(new IntegerType(), value);
	}
	
	@Override
	public Type accept(QLNodeVisitor visitor) {
		return visitor.visit(this);

	}

}
