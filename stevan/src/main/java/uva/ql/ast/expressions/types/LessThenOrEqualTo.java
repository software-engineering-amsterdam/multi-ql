package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class LessThenOrEqualTo extends Type {

	private String type = "lessthenorequalto";

	@Override
	public String getType() {
		return this.type;
	}
}
