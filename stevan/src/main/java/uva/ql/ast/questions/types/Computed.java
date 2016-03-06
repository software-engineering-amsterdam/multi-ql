package uva.ql.ast.questions.types;

import uva.ql.ast.abstracts.Type;

public class Computed extends Type {

	private String type = "computed";

	@Override
	public String getType() {
		return this.type;
	}
}
