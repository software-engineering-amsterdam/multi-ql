package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class And extends Type {

	private String type = "and";

	@Override
	public String getType() {
		return this.type;
	}
}
