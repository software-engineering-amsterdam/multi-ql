package ast.model;

import java.util.LinkedList;
import java.util.List;

import ast.visitor.FormVisitor;

public class Box extends AbstractNode {
	List<Statement> statements;
	
	public List<Statement> getStatements(){
		return statements;
	}
	
	public void addStatement(Statement statement) {
		if (statements == null) {
			this.statements = new LinkedList<>();
		}
		this.statements.add(statement);
	}
	
	public void accept(FormVisitor formVisitor){
		formVisitor.visit(this);
	}
}
