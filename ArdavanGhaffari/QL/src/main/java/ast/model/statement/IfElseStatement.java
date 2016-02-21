package ast.model.statement;

import ast.model.Box;
import ast.model.Expression;
import ast.visitor.StatementVisitor;

public class IfElseStatement extends IfStatement {
	private Box elseBox;
	
	public IfElseStatement(Expression expression, Box ifBox, Box elseBox) {
		super(expression, ifBox);
		this.elseBox = elseBox;
	}
	
	public Box getElseBox() {
		return this.elseBox;
	}
	
	@Override
	public void accept(StatementVisitor statementVisitor) {
		statementVisitor.visit(this);
	}
}
