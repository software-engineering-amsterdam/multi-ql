package uva.ql.ast.conditionals.types;

import uva.ql.ast.abstracts.Type;

public class IfElseStatement extends Type {

	private String type = "ifelsestatement";

	@Override
	public String getType() {
		return this.type;
	}

}
