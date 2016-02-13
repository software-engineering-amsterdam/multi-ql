package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.QuestionLabel;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.type.Type;

public class Question extends Statement {

	IdentifierLit id;
	QuestionLabel label;
	Type type;

	public Question(IdentifierLit id, QuestionLabel label, Type type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}

	public IdentifierLit getId() {
		return id;
	}

	public QuestionLabel getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}
		
}
