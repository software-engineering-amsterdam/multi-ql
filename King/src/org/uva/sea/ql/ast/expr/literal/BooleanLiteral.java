package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.type.BooleanType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(boolean value) {
		super(new BooleanType(), value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Type accept(QLNodeVisitor visitor) {
		return visitor.visit(this);

	}

}
