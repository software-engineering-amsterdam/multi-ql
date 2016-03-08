package uva.ql.ast.variables.types;

import uva.ql.ast.abstracts.Type;


public class Date extends Type {

	private String type = "date";

	@Override
	public String getType() {
		return this.type;
	}
}
