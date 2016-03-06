package uva.ql.ast.expressions.types;

import uva.ql.ast.abstracts.Type;


public class LessThen extends Type {

	private String type = "lessthen";

	@Override
	public String getType() {
		return this.type;
	}
}
