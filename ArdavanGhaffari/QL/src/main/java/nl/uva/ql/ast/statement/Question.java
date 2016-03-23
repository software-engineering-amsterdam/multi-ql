package nl.uva.ql.ast.statement;

import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.ast.type.Type;
import nl.uva.ql.visitors.StatementVisitor;

public class Question extends Statement {
	Identifier identifier;
	String label;
	Type type;
	
	public Question(Identifier identifier, String label, Type type, int line) {
		super(line);
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

	public <T> T accept(StatementVisitor<T> statementVisitor){
		return statementVisitor.visit(this);
	}
}
