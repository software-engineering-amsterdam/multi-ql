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

public class BasicVisitor implements Visitor<Object> {
	private Visitor<Object> visitor;

	public BasicVisitor() {
		visitor = this;
	}

	@Override
	public Object visit(Form form) {
		form.getBody().accept(visitor);
		return null;
	}

	@Override
	public Object visit(Body body) {
		for (Visitable v : body.getStatements()) {
			v.accept(visitor);
		}
		;
		return null;
	}

	@Override
	public Object visit(Statement statement) {
		if (statement.getAssignmentQuestion() != null) {
			return statement.getAssignmentQuestion().accept(visitor);
		}
		if (statement.getIfStatement() != null) {
			return statement.getIfStatement().accept(visitor);
		}
		if (statement.getQuestion() != null) {
			return statement.getQuestion().accept(visitor);
		}
		return null;
	}

	@Override
	public Object visit(AssignmentQuestion assignementQuestion) {
		assignementQuestion.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement) {
		ifStatement.getExpression().accept(visitor);
		ifStatement.getBody().accept(visitor);
		return null;
	}

	@Override
	public Object visit(Question question) {
		// TODO: moet ik hier nog verder een visit doen op de variable?
		question.getVariable().accept(visitor);
		return null;
	}

	@Override
	public Object visit(BinaryExpression binaryExpression) {
		binaryExpression.getLhs().accept(visitor);
		binaryExpression.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Object visit(OrExpression orExpression) {
		visit((BinaryExpression) orExpression);
		return null;
	}

	@Override
	public Object visit(AndExpression andExpression) {
		visit((BinaryExpression) andExpression);
		return null;
	}

	@Override
	public Object visit(Add add) {
		visit((BinaryExpression) add);
		return null;
	}

	@Override
	public Object visit(Div div) {
		visit((BinaryExpression) div);
		return null;
	}

	@Override
	public Object visit(Eq eq) {
		visit((BinaryExpression) eq);
		return null;
	}

	@Override
	public Object visit(GEq geq) {
		visit((BinaryExpression) geq);
		return null;
	}

	@Override
	public Object visit(GT gt) {
		visit((BinaryExpression) gt);
		return null;
	}

	@Override
	public Object visit(LEq leq) {
		visit((BinaryExpression) leq);
		return null;
	}

	@Override
	public Object visit(LT lt) {
		visit((BinaryExpression) lt);
		return null;
	}

	@Override
	public Object visit(Mul mul) {
		visit((BinaryExpression) mul);
		return null;
	}

	@Override
	public Object visit(NEq neq) {
		visit((BinaryExpression) neq);
		return null;
	}

	@Override
	public Object visit(Neg neg) {
		neg.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Object visit(Not not) {
		not.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Object visit(Pos pos) {
		pos.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Object visit(Sub sub) {
		visit((BinaryExpression) sub);
		return null;
	}

	@Override
	public Object visit(Literal literal) {
		if (literal.getBoolLiteral() != null) {
			return literal.getBoolLiteral().accept(visitor);
		}
		if (literal.getIntLiteral() != null) {
			return literal.getIntLiteral().accept(visitor);
		}
		if (literal.getStringLiteral() != null) {
			return literal.getStringLiteral().accept(visitor);
		}
		if (literal.getVariableExpression() != null) {
			return literal.getVariableExpression().accept(visitor);
		}
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral) {
		return null;
	}

	@Override
	public Object visit(BoolLiteral boolLiteral) {
		return null;
	}

	@Override
	public Object visit(StringLiteral stringLiteral) {
		return null;
	}

	@Override
	public Object visit(Variable variable) {
		return null;
	}

	@Override
	public Object visit(VariableExpression variableExpression) {
		return null;
	}
}
