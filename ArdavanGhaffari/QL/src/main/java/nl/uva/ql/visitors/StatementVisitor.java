package nl.uva.ql.visitors;

import nl.uva.ql.ast.Box;
import nl.uva.ql.ast.statement.ComputedQuestion;
import nl.uva.ql.ast.statement.IfElseStatement;
import nl.uva.ql.ast.statement.IfStatement;
import nl.uva.ql.ast.statement.Question;

public interface StatementVisitor<T> {
	public T visit(Question question);
	public T visit(ComputedQuestion computedQuestion);
	public T visit(IfStatement ifStatement);
	public T visit(IfElseStatement ifElseStatement);
	public T visit(Box box);
}
