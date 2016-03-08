package uva.ql.ast.variables.types;

import uva.ql.ast.abstracts.Type;


public class Money extends Type {

	private String type = "money";

	@Override
	public String getType() {
		return this.type;
	}
}
