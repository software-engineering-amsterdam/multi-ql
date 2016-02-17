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
import ast.expression.NEq;
import ast.expression.Neg;
import ast.expression.Not;
import ast.expression.OrExpression;
import ast.expression.Pos;
import ast.expression.Sub;
import ast.expression.VariableExpression;
import ast.form.Body;
import ast.form.Form;
import ast.literal.BoolLiteral;
import ast.literal.IntLiteral;
import ast.literal.Literal;
import ast.literal.StringLiteral;
import ast.literal.Variable;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;
import ast.statement.Statement;

public interface Visitor {
	public Types visit(Form form);
	public Types visit(Body body);
	
	public Types visit(AssignmentQuestion assignementQuestion);
	public Types visit(IfStatement ifStatement);
	public Types visit(Question question);
	public Types visit(Statement statement);
	
	public Types visit(Add add);
	public Types visit(AndExpression andExpression);
	public Types visit(Div div);
	public Types visit(Eq eq);
	public Types visit(GEq geq);
	public Types visit(GT gt);
	public Types visit(LEq leq);
	public Types visit(LT lt);
	public Types visit(Mul mul);
	public Types visit(NEq neq);
	public Types visit(Neg neg);
	public Types visit(Not not);
	public Types visit(OrExpression orExpression);
	public Types visit(Pos pos);
	public Types visit(Sub sub);
	public Types visit(VariableExpression variableExpression);
	
	public Types visit(IntLiteral intLiteral);
	public Types visit(BoolLiteral boolLiteral);
	public Types visit(StringLiteral stringLiteral);
	public Types visit(Literal literal);
	public Types visit(Variable variable);
}
