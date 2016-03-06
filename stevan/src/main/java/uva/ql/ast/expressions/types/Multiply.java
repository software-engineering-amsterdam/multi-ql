package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;

public class Multiply extends Type {

	private String type = "multiply";

	@Override
	public String getType() {
		return this.type;
	}
}
