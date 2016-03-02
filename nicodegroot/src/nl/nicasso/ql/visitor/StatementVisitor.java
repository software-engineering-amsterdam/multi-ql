package nl.nicasso.ql.visitor;

import nl.nicasso.ql.ast.statement.ComputedQuestion;
import nl.nicasso.ql.ast.statement.IfElseStatement;
import nl.nicasso.ql.ast.statement.IfStatement;
import nl.nicasso.ql.ast.statement.Question;

public interface StatementVisitor<T> {

	public T visit(Question value);
	public T visit(ComputedQuestion value);	
	public T visit(IfStatement value);
	public T visit(IfElseStatement value);
	
}
