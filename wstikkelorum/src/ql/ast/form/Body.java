package ql.ast.form;

import java.util.ArrayList;
import java.util.List;

import ql.ast.ASTNode;
import ql.ast.statement.Statement;
import ql.ast.visitor.Visitable;
import ql.ast.visitor.Visitor;

public class Body extends ASTNode implements Visitable {
	private List<Statement> statements;

	public Body() {
		super(1);//lineNumber
		this.statements = new ArrayList<Statement>();
	}

	public void add(Statement result) {
		this.statements.add(result);
	}

	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);

	}
}
