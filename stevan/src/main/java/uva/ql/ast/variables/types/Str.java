package uva.ql.ast.variables.types;

import uva.ql.ast.abstracts.Type;


public class Str extends Type {

	private String type = "string";

	@Override
	public String getType() {
		return this.type;
	}
}
