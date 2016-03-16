package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.statements.IfElseStatement;
import nl.nicasso.ql.ast.nodes.statements.IfStatement;
import nl.nicasso.ql.ast.nodes.statements.Question;

public interface StatementVisitor<T, U> {

	// @TODO FIX ALL THESE EXTRA PARAMS, BUT NOT EVERYWHERE! ONLY WHERE THEY ARE NEEDED FOR THE CYCLOMATIC DEPENDNCY
	public T visit(Question value, U context);
	public T visit(ComputedQuestion value, U context);	
	public T visit(IfStatement value, U context);
	public T visit(IfElseStatement value, U context);
	
}