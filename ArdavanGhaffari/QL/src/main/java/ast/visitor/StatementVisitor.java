package ast.visitor;

import ast.model.statement.ComputedQuestion;
import ast.model.statement.IfElseStatement;
import ast.model.statement.IfStatement;
import ast.model.statement.Question;

public interface StatementVisitor {
	public void visit(Question question);
	public void visit(ComputedQuestion computedQuestion);
	public void visit(IfStatement ifStatement);
	public void visit(IfElseStatement ifElseStatement);
}
