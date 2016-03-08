package uva.ql.ast.variables.types;

import uva.ql.ast.abstracts.Type;


public class Decimal extends Type {

	private String type = "decimal";

	@Override
	public String getType() {
		return this.type;
	}
}
