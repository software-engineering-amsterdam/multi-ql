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
import ast.statement.ComputedQuestion;
import ast.statement.IfStatement;
import ast.statement.InputQuestion;
import ast.statement.Question;
import ast.statement.Statement;

public class BasicVisitor<T> implements Visitor<T> {
	public BasicVisitor() {
	}

	@Override
	public T visit(Form form) {
		form.getBody().accept(this);
		return null;
	}

	@Override
	public T visit(Body body) {
		for (Visitable v : body.getStatements()) {
			v.accept(this);
		}
		return null;
	}

	@Override
	public T visit(Statement statement) {
		if (statement.getIfStatement() != null) {
			return statement.getIfStatement().accept(this);
		}
		if (statement.getQuestion() != null) {
			return statement.getQuestion().accept(this);
		}
		return null;
	}
	
	@Override
	public T visit(Question question) {
		question.getVariable().accept(this);
		return null;
	}

	@Override
	public T visit(ComputedQuestion assignementQuestion) {
		return assignementQuestion.getExpression().accept(this);
	}
	
	@Override
	public T visit(InputQuestion inputQuestion){
		return inputQuestion.getVariable().accept(this);
	}

	@Override
	public T visit(IfStatement ifStatement) {
		ifStatement.getExpression().accept(this);
		ifStatement.getBody().accept(this);
		return null;
	}

	@Override
	public T visit(BinaryExpression binaryExpression) {
		binaryExpression.getLhs().accept(this);
		binaryExpression.getRhs().accept(this);
		return null;//TODO: Dit is misschien niet goed???
	}

	@Override
	public T visit(OrExpression orExpression) {
		return visit((BinaryExpression) orExpression);
	}

	@Override
	public T visit(AndExpression andExpression) {
		return visit((BinaryExpression) andExpression);
	}

	@Override
	public T visit(Add add) {
		return visit((BinaryExpression) add);
	}

	@Override
	public T visit(Div div) {
		return visit((BinaryExpression) div);
	}

	@Override
	public T visit(Eq eq) {
		return visit((BinaryExpression) eq);
	}

	@Override
	public T visit(GEq geq) {
		return visit((BinaryExpression) geq);
	}

	@Override
	public T visit(GT gt) {
		return visit((BinaryExpression) gt);
	}

	@Override
	public T visit(LEq leq) {
		return visit((BinaryExpression) leq);
	}

	@Override
	public T visit(LT lt) {
		return visit((BinaryExpression) lt);
	}

	@Override
	public T visit(Mul mul) {
		return visit((BinaryExpression) mul);
	}

	@Override
	public T visit(NEq neq) {
		return visit((BinaryExpression) neq);
	}

	@Override
	public T visit(Neg neg) {
		return neg.getExpression().accept(this);
	}

	@Override
	public T visit(Not not) {
		return not.getExpression().accept(this);
	}

	@Override
	public T visit(Pos pos) {
		return pos.getExpression().accept(this);
	}

	@Override
	public T visit(Sub sub) {
		return visit((BinaryExpression) sub);
	}

	@Override
	public T visit(Literal literal) {
		if (literal.getBoolLiteral() != null) {
			return literal.getBoolLiteral().accept(this);
		}
		if (literal.getIntLiteral() != null) {
			return literal.getIntLiteral().accept(this);
		}
		if (literal.getStringLiteral() != null) {
			return literal.getStringLiteral().accept(this);
		}
		if (literal.getVariableExpression() != null) {
			return literal.getVariableExpression().accept(this);
		}
		return null;
	}

	@Override
	public T visit(IntLiteral intLiteral) {
		return null;
	}

	@Override
	public T visit(BoolLiteral boolLiteral) {
		return null;
	}

	@Override
	public T visit(StringLiteral stringLiteral) {
		return null;
	}

	@Override
	public T visit(Variable variable) {
		return null;
	}

	@Override
	public T visit(VariableExpression variableExpression) {
		return null;
	}
}
