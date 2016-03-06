package uva.ql.ast.variables.types;

import uva.ql.ast.abstracts.Type;


public class Bool extends Type {

	private String type = "boolean";

	@Override
	public String getType() {
		return this.type;
	}
}
