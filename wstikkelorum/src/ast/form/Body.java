package ast.form;

import java.util.ArrayList;
import java.util.List;

import ast.statement.Statement;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Body implements Visitable {
	private List<Statement> statements;
	
	public Body(){
		this.statements = new ArrayList<Statement>();
	}
	public void add(Statement result) {
		this.statements.add(result);
	}
	
	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
		
	}
}
