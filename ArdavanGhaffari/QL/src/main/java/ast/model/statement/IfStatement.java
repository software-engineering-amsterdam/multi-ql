package ast.model.statement;

import ast.model.Box;
import ast.model.Expression;
import ast.model.Statement;
import ast.visitor.StatementVisitor;

public class IfStatement extends Statement {
	private Expression expression;
	private Box ifBox;
	
	public IfStatement(Expression expression, Box box) {
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
	public void accept(StatementVisitor statementVisitor) {
		statementVisitor.visit(this);
	}
	
}
