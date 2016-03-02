package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Identifier;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.StatementVisitor;

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
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		ComputedQuestion q2 = (ComputedQuestion) ob;
		return id.equals(q2.getId());
	}
	
	@Override
	public int hashCode(){
	    return id.hashCode();
    }
		
}
