package ast.visitor;

import ast.expression.Add;
import ast.expression.AndExpression;
import ast.expression.BinaryExpression;
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

public interface Visitor<T> {
	public T visit(Form form);

	public T visit(Body body);

	public T visit(AssignmentQuestion assignementQuestion);

	public T visit(IfStatement ifStatement);

	public T visit(Question question);

	public T visit(Statement statement);

	public T visit(Add add);

	public T visit(AndExpression andExpression);

	public T visit(Div div);

	public T visit(Eq eq);

	public T visit(BinaryExpression binaryExpression);

	public T visit(GEq geq);

	public T visit(GT gt);

	public T visit(LEq leq);

	public T visit(LT lt);

	public T visit(Mul mul);

	public T visit(NEq neq);

	public T visit(Neg neg);

	public T visit(Not not);

	public T visit(OrExpression orExpression);

	public T visit(Pos pos);

	public T visit(Sub sub);

	public T visit(VariableExpression variableExpression);

	public T visit(IntLiteral intLiteral);

	public T visit(BoolLiteral boolLiteral);

	public T visit(StringLiteral stringLiteral);

	public T visit(Literal literal);

	public T visit(Variable variable);
}
