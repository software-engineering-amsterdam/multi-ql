package nl.uva.ql.ast.statement;

import nl.uva.ql.ast.Box;
import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.StatementVisitor;

public class IfElseStatement extends IfStatement {
	private Box elseBox;
	
	public IfElseStatement(Expression expression, Box ifBox, Box elseBox, int line) {
		super(expression, ifBox, line);
		this.elseBox = elseBox;
	}
	
	public Box getElseBox() {
		return this.elseBox;
	}
	
	@Override
	public <T> T accept(StatementVisitor<T> statementVisitor){
		return statementVisitor.visit(this);
	}
}
