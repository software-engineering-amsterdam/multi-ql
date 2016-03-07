package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class EqualTo extends Type {

	private String type = "equalto";

	@Override
	public String getType() {
		return this.type;
	}
}
