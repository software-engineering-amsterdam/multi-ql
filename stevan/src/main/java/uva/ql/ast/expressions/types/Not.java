package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class Not extends Type {

	private String type = "not";

	@Override
	public String getType() {
		return this.type;
	}
}
