package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.type.StringType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class StringLiteral extends Literal<String> {

	public StringLiteral(String value) {
		super(new StringType(), value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Type accept(QLNodeVisitor visitor) {
		return visitor.visit(this);

	}

}
