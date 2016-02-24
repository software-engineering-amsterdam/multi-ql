package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.type.Type;

public class Question extends Statement implements Traversable {

	private final IdentifierLit id;
	private final String label;
	private final Type type;

	public Question(IdentifierLit id, String label, Type type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}

	public IdentifierLit getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Type accept(TypeCheckerVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Literal accept(EvaluatorVisitor visitor) {
		return visitor.visit(this);
	}
		
}
