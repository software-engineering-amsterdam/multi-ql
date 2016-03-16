package nl.nicasso.ql.ast.nodes.statements;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.StatementVisitor;

public class Question extends Statement {

	private final Identifier id;
	private final String label;
	private final Type type;

	public Question(Identifier id, String label, Type type, CodeLocation location) {
		super(location);
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
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
	
	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof Question)) {
			return false;
		}
		Question q2 = (ComputedQuestion) ob;
		return id.equals(q2.getId());
	}
	
	@Override
	public int hashCode(){
	    return id.hashCode();
    }
		
}
