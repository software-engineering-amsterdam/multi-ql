package uva.ql.ast.conditionals.types;

import uva.ql.ast.abstracts.Type;

public class IfStatement extends Type {

	private String type = "ifstatement";

	@Override
	public String getType() {
		return this.type;
	}
}
