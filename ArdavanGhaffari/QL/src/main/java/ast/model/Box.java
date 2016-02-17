package ast.model;

import java.util.LinkedList;
import java.util.List;

import ast.model.statement.Statement;

public class Box extends AbstractNode {
	List<Statement> statements;
	
	public void addStatement(Statement statement) {
		if (statements == null) {
			this.statements = new LinkedList<>();
		}
		this.statements.add(statement);
	}
}
