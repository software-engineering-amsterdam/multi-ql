package nl.uva.ql.ast.statement;

import nl.uva.ql.ast.Box;
import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.StatementVisitor;

public class IfStatement extends Statement {
	private Expression expression;
	private Box ifBox;
	
	public IfStatement(Expression expression, Box box, int line) {
		super(line);
		this.expression = expression;
		this.ifBox = box;
	}

	public Expression getExpression() {
		return expression;
	}

	public Box getIfBox() {
		return ifBox;
	}
	
	@Override
	public <T> T accept(StatementVisitor<T> statementVisitor){
		return statementVisitor.visit(this);
	}
	
}
