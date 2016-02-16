package ast.model.statement;

import ast.model.expression.literal.Identifier;
import ast.model.type.Type;

public class Question extends Statement {
	Identifier identifier;
	String label;
	Type type;
	
	public Question(Identifier identifier, String label, Type type) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
	}
}
