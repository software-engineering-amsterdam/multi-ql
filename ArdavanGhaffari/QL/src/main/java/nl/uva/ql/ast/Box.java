package nl.uva.ql.ast;

import java.util.List;
import nl.uva.ql.ast.statement.Statement;
import nl.uva.ql.visitors.StatementVisitor;

public class Box extends AbstractNode {
	List<Statement> statements;
	
	public Box(int line, List<Statement> statements) {
		super(line);
		this.statements = statements;
	}
	
	public List<Statement> getStatements(){
		return statements;
	}
	
	public <T> T accept(StatementVisitor<T> statementVisitor){
		return statementVisitor.visit(this);
	}
}
