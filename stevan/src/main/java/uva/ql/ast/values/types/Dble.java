package uva.ql.ast.values.types;

import uva.ql.ast.abstracts.Type;


public class Dble extends Type {

	private String type = "double";

	@Override
	public String getType() {
		return this.type;
	}
}
