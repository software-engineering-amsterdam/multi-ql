package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class parentheses extends Type {

	private String type = "add";

	@Override
	public String getType() {
		return this.type;
	}
}
