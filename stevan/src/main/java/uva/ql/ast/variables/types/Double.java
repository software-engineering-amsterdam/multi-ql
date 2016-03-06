package uva.ql.ast.variables.types;

import uva.ql.ast.abstracts.Type;


public class Double extends Type {

	private String type = "double";

	@Override
	public String getType() {
		return this.type;
	}
}
