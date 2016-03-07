package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class NotEqualTo extends Type {

	private String type = "notequalto";

	@Override
	public String getType() {
		return this.type;
	}
}
