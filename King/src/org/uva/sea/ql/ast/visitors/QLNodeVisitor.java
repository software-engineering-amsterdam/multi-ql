package org.uva.sea.ql.ast.visitors;

import org.uva.sea.ql.ast.expr.VarExpr;
import org.uva.sea.ql.ast.expr.binary.AND;
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

public interface QLNodeVisitor<T> {

	public T visit(Add add, boolean context);

	public T visit(AND and, boolean context);

	public T visit(Div div, boolean context);

	public T visit(Equal eq, boolean context);

	public T visit(GreaterOrEqual geq, boolean context);

	public T visit(GreaterThan gt, boolean context);

	public T visit(SmallerOrEqual leq, boolean context);

	public T visit(SmallerThan lt, boolean context);

	public T visit(Mul mul, boolean context);

	public T visit(NotEqual neq, boolean context);

	public T visit(Negative neg, boolean context);

	public T visit(NOT not, boolean context);

	public T visit(OR or, boolean context);

	public T visit(Positive pos, boolean context);

	public T visit(Sub sub, boolean context);

	public T visit(IntegerLiteral intLiteral, boolean context);

	public T visit(BooleanLiteral boolLiteral, boolean context);

	public T visit(StringLiteral stringLiteral, boolean context);

	public T visit(MoneyLiteral moneyLiteral, boolean context);

	public T visit(VarExpr varExpr, boolean context);

}
