package ast.form;

import java.util.ArrayList;
import java.util.List;

import ast.TreeNode;
import ast.statement.Statement;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Body extends TreeNode implements Visitable {
	private List<Statement> statements;

	public Body() {
		super(1);// TODO:???????????
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
