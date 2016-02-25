package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Identifier;
import nl.nicasso.ql.ast.type.Type;

public class Question extends Statement implements Traversable {

	private final Identifier id;
	private final String label;
	private final Type type;

	public Question(Identifier id, String label, Type type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}

	public Identifier getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
		
}
