package ast.model.statement;

import ast.model.Statement;
import ast.model.literal.Identifier;
import ast.model.type.Type;
import ast.visitor.StatementVisitor;

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

	@Override
	public void accept(StatementVisitor statementVisitor) {
		statementVisitor.visit(this);
	}
}
