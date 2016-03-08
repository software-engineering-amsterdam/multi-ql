package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;

public class Minus extends Type {

	private String type = "minus";

	@Override
	public String getType() {
		return this.type;
	}
}
