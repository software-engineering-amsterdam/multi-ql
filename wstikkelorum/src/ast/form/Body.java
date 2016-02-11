package ast.form;

import ast.statement.Statement;

public class Body {
	private Statement statement;
	
	public void add(Statement result) {
		this.statement = result;
	}
}
