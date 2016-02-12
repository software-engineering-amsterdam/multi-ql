package ast.visitor;

import ast.expression.Add;
import ast.expression.AndExpression;
import ast.expression.Div;
import ast.expression.Eq;
import ast.expression.GEq;
import ast.expression.GT;
import ast.expression.LEq;
import ast.expression.LT;
import ast.expression.Mul;
import ast.expression.Neg;
import ast.expression.NEq;
import ast.expression.Not;
import ast.expression.OrExpression;
import ast.expression.Pos;
import ast.expression.Sub;
import ast.form.Body;
import ast.form.Form;
import ast.literal.IntLiteral;
import ast.literal.Literal;
import ast.literal.Variable;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;
import ast.statement.Statement;

public interface Visitor {
	public void visit(Form form);
	public void visit(Body body);
	
	public void visit(Add add);
	public void visit(AndExpression andExpression);
	public void visit(Div div);
	public void visit(Eq eq);
	public void visit(GEq geq);
	public void visit(GT gt);
	public void visit(LEq leq);
	public void visit(LT lt);
	public void visit(Mul mul);
	public void visit(NEq neq);
	public void visit(Neg neg);
	public void visit(Not not);
	public void visit(OrExpression orExpression);
	public void visit(Pos pos);
	public void visit(Sub sub);
	
	public void visit(IntLiteral intLiteral);
	public void visit(Literal literal);
	public void visit(Variable variable);
	
	public void visit(AssignmentQuestion assignementQuestion);
	public void visit(IfStatement ifStatement);
	public void visit(Question question);
	public void visit(Statement statement);
}
