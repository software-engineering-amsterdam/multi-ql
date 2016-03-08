package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class GreaterThen extends Type {

	private String type = "greaterthen";

	@Override
	public String getType() {
		return this.type;
	}
}
