package ast.form;

import java.util.ArrayList;
import java.util.List;

import ast.Visitable;
import ast.Visitor;
import ast.statement.Statement;

public class Body implements Visitable {
	private List<Statement> statements;
	
	public Body(){
		this.statements = new ArrayList<Statement>();
	}

	public void add(Statement result) {
		this.statements.add(result);
	}
	
	public List<Statement> getStatement() {
		return statements;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
}
