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
	public Set<String> visit(Add add) {
		return visitBinaryExpression(add);
	}

	@Override
	public Set<String> visit(AND and) {
		return visitBinaryExpression(and);
	}

	@Override
	public Set<String> visit(Div div) {
		return visitBinaryExpression(div);
	}

	@Override
	public Set<String> visit(Equal eq) {
		return visitBinaryExpression(eq);
	}

	@Override
	public Set<String> visit(GreaterOrEqual geq) {
		return visitBinaryExpression(geq);
	}

	@Override
	public Set<String> visit(GreaterThan gt) {
		return visitBinaryExpression(gt);
	}

	@Override
	public Set<String> visit(SmallerOrEqual leq) {
		return visitBinaryExpression(leq);
	}

	@Override
	public Set<String> visit(SmallerThan lt) {
		return visitBinaryExpression(lt);
	}

	@Override
	public Set<String> visit(Mul mul) {
		return visitBinaryExpression(mul);
	}

	@Override
	public Set<String> visit(NotEqual neq) {
		return visitBinaryExpression(neq);
	}

	@Override
	public Set<String> visit(Negative neg) {
		return visitUnaryExpression(neg);
	}

	@Override
	public Set<String> visit(NOT not) {
		return visitUnaryExpression(not);
	}

	@Override
	public Set<String> visit(OR or) {
		return visitBinaryExpression(or);
	}

	@Override
	public Set<String> visit(Positive pos) {
		return visitUnaryExpression(pos);
	}

	@Override
	public Set<String> visit(Sub sub) {
		return visitBinaryExpression(sub);
	}

	@Override
	public Set<String> visit(IntegerLiteral intLiteral) {
		return identifiers;
	}

	@Override
	public Set<String> visit(BooleanLiteral boolLiteral) {
		return identifiers;
	}

	@Override
	public Set<String> visit(StringLiteral stringLiteral) {
		return identifiers;
	}

	@Override
	public Set<String> visit(MoneyLiteral moneyLiteral) {
		return identifiers;
	}

	@Override
	public Set<String> visit(VarExpr varExpr) {
		identifiers.add(varExpr.getIdentifier().getName());
		return identifiers;
	}

	
	public Set<String> visitBinaryExpression(BinaryExpression binaryExpression) {
		binaryExpression.getFirstExpression().accept(this);
		binaryExpression.getSecondExpression().accept(this);
		return identifiers;
	}

	
	public Set<String> visitUnaryExpression(UnaryExpression unaryExpression) {
		unaryExpression.getExpression().accept(this);
		return identifiers;
	}

}
