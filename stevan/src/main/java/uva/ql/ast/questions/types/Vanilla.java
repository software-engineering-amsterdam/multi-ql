package uva.ql.ast.questions.types;

import uva.ql.ast.abstracts.Type;

public class Vanilla extends Type {

	private String type = "vanilla";

	@Override
	public String getType() {
		return this.type;
	}
}
