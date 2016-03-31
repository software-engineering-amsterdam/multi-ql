package nl.nicasso.ql.ast.nodes.statements;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.visitors.StatementVisitor;

public class Question extends Statement {

	private final Identifier identifier;
	private final String label;
	private final Type type;

	public Question(Identifier identifier, String label, Type type, CodeLocation location) {
		super(location);
		this.identifier = identifier;
		this.label = label;
		this.type = type;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public String getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}

	public Value getDefaultValue() {
		return type.getDefaultValue();
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof Question)) {
			return false;
		}
		Question q2 = (ComputedQuestion) ob;
		return identifier.equals(q2.getIdentifier());
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

}