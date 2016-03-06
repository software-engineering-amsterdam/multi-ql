package uva.ql.ast.variables.types;

import uva.ql.ast.abstracts.Type;


public class Generic extends Type {

	private String type = "generic";

	@Override
	public String getType() {
		return this.type;
	}
}
