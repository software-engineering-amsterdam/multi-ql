package ql.ast.literal;

import ql.ast.expression.Expression;
import ql.ast.expression.VariableExpression;
import ql.ast.visitor.Visitor;

public class Literal extends Expression {
	private IntLiteral intLiteral;
	private BoolLiteral boolLiteral;
	private StringLiteral stringLiteral;
	private VariableExpression variableExpression;

	public Literal(IntLiteral result) {
		super(result.getLineNumber());
		this.intLiteral = result;
	}

	public Literal(BoolLiteral result) {
		super(result.getLineNumber());
		this.boolLiteral = result;
	}

	public Literal(StringLiteral result) {
		super(result.getLineNumber());
		this.stringLiteral = result;
	}

	public Literal(VariableExpression result) {
		super(result.getLineNumber());
		this.variableExpression = result;
	}

	public IntLiteral getIntLiteral() {
		return intLiteral;
	}

	public BoolLiteral getBoolLiteral() {
		return boolLiteral;
	}

	public StringLiteral getStringLiteral() {
		return stringLiteral;
	}

	public VariableExpression getVariableExpression() {
		return variableExpression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
