package org.uva.sea.ql.semantic;

import java.util.HashSet;
import java.util.Set;
import org.uva.sea.ql.ast.expr.VarExpr;
import org.uva.sea.ql.ast.expr.binary.AND;
import org.uva.sea.ql.ast.expr.binary.BinaryExpression;
import org.uva.sea.ql.ast.expr.binary.Equal;
import org.uva.sea.ql.ast.expr.binary.GreaterOrEqual;
import org.uva.sea.ql.ast.expr.binary.GreaterThan;
import org.uva.sea.ql.ast.expr.binary.NotEqual;
import org.uva.sea.ql.ast.expr.binary.OR;
import org.uva.sea.ql.ast.expr.binary.SmallerOrEqual;
import org.uva.sea.ql.ast.expr.binary.SmallerThan;
import org.uva.sea.ql.ast.expr.literal.BooleanLiteral;
import org.uva.sea.ql.ast.expr.literal.IntegerLiteral;
import org.uva.sea.ql.ast.expr.literal.MoneyLiteral;
import org.uva.sea.ql.ast.expr.literal.StringLiteral;
import org.uva.sea.ql.ast.expr.math.Add;
import org.uva.sea.ql.ast.expr.math.Div;
import org.uva.sea.ql.ast.expr.math.Mul;
import org.uva.sea.ql.ast.expr.math.Sub;
import org.uva.sea.ql.ast.expr.unary.NOT;
import org.uva.sea.ql.ast.expr.unary.Negative;
import org.uva.sea.ql.ast.expr.unary.Positive;
import org.uva.sea.ql.ast.expr.unary.UnaryExpression;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class IdentifierDependency implements QLNodeVisitor<Set<String>> {
	private Set<String> identifiers;

	public IdentifierDependency() {
		identifiers = new HashSet<>();
	}

	@Override
	public Set<String> visit(Add add, boolean context) {
		return visitBinaryExpression(add,context);
	}

	@Override
	public Set<String> visit(AND and, boolean context) {
		return visitBinaryExpression(and,context);
	}

	@Override
	public Set<String> visit(Div div, boolean context) {
		return visitBinaryExpression(div,context);
	}

	@Override
	public Set<String> visit(Equal eq, boolean context) {
		return visitBinaryExpression(eq,context);
	}

	@Override
	public Set<String> visit(GreaterOrEqual geq, boolean context) {
		return visitBinaryExpression(geq,context);
	}

	@Override
	public Set<String> visit(GreaterThan gt, boolean context) {
		return visitBinaryExpression(gt,context);
	}

	@Override
	public Set<String> visit(SmallerOrEqual leq, boolean context) {
		return visitBinaryExpression(leq,context);
	}

	@Override
	public Set<String> visit(SmallerThan lt, boolean context) {
		return visitBinaryExpression(lt,context);
	}

	@Override
	public Set<String> visit(Mul mul, boolean context) {
		return visitBinaryExpression(mul,context);
	}

	@Override
	public Set<String> visit(NotEqual neq, boolean context) {
		return visitBinaryExpression(neq,context);
	}

	@Override
	public Set<String> visit(Negative neg, boolean context) {
		return visitUnaryExpression(neg,context);
	}

	@Override
	public Set<String> visit(NOT not, boolean context) {
		return visitUnaryExpression(not,context);
	}

	@Override
	public Set<String> visit(OR or, boolean context) {
		return visitBinaryExpression(or,context);
	}

	@Override
	public Set<String> visit(Positive pos, boolean context) {
		return visitUnaryExpression(pos,context);
	}

	@Override
	public Set<String> visit(Sub sub, boolean context) {
		return visitBinaryExpression(sub,context);
	}

	@Override
	public Set<String> visit(IntegerLiteral intLiteral, boolean context) {
		return identifiers;
	}

	@Override
	public Set<String> visit(BooleanLiteral boolLiteral, boolean context) {
		return identifiers;
	}

	@Override
	public Set<String> visit(StringLiteral stringLiteral, boolean context) {
		return identifiers;
	}

	@Override
	public Set<String> visit(MoneyLiteral moneyLiteral, boolean context) {
		return identifiers;
	}

	@Override
	public Set<String> visit(VarExpr varExpr, boolean context) {
		identifiers.add(varExpr.getIdentifier().getName());
		return identifiers;
	}

	public Set<String> visitBinaryExpression(BinaryExpression binaryExpression, boolean context) {
		binaryExpression.getFirstExpression().accept(this,context);
		binaryExpression.getSecondExpression().accept(this,context);
		return identifiers;
	}

	public Set<String> visitUnaryExpression(UnaryExpression unaryExpression, boolean context) {
		unaryExpression.getExpression().accept(this,context);
		return identifiers;
	}

}
