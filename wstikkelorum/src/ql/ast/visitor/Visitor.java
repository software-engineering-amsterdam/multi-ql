package ql.ast.visitor;

import ql.ast.expression.Add;
import ql.ast.expression.AndExpression;
import ql.ast.expression.BinaryExpression;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.OrExpression;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.expression.VariableExpression;
import ql.ast.form.Body;
import ql.ast.form.Form;
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntLiteral;
import ql.ast.literal.StringLiteral;
import ql.ast.literal.Variable;
import ql.ast.statement.IfStatement;
import ql.ast.statement.Statement;
import ql.ast.statement.question.ComputedQuestion;
import ql.ast.statement.question.InputQuestion;
import ql.ast.statement.question.Question;
import ql.ast.value.BooleanValue;
import ql.ast.value.IntegerValue;
import ql.ast.value.NullValue;
import ql.ast.value.StringValue;

public interface Visitor<T> {
	public T visit(Form form);

	public T visit(Body body);

	public T visit(ComputedQuestion computedQuestion);

	public T visit(InputQuestion inputQuestion);

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

	public T visit(Variable variable);
	
	public T visit(BooleanValue booleanValue);
	
	public T visit(IntegerValue integerValue);
	
	public T visit(StringValue stringValue);
	
	public T visit(NullValue nullValue);
}
