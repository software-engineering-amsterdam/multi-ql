package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.statements.IfElseStatement;
import nl.nicasso.ql.ast.nodes.statements.IfStatement;
import nl.nicasso.ql.ast.nodes.statements.Question;

public interface StatementVisitor<T, U> {

	public T visit(Question statement, U context);
	public T visit(ComputedQuestion statement, U context);
	public T visit(IfStatement statement, U context);
	public T visit(IfElseStatement statement, U context);

}