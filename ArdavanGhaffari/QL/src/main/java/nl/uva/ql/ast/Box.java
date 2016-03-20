package nl.uva.ql.ast;

import java.util.LinkedList;
import java.util.List;

import nl.uva.ql.ast.statement.Statement;
import nl.uva.ql.visitors.StatementVisitor;

public class Box extends AbstractNode {
	List<Statement> statements;
	
	public Box(int line) {
		super(line);
		this.statements = new LinkedList<>();
	}
	
	public List<Statement> getStatements(){
		return statements;
	}
	
	public void addStatement(Statement statement) {
		this.statements.add(statement);
	}
	
	public <T> T accept(StatementVisitor<T> statementVisitor){
		return statementVisitor.visit(this);
	}
}
