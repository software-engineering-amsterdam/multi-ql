package org.uva.sea.ql.ast.visitors;

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

public interface QLNodeVisitor<T> {
	
	public T visit(Add add);
	
	public T visit(AND and);

	public T visit(Div div);

	public T visit(Equal eq);

	public T visit(GreaterOrEqual geq);

	public T visit(GreaterThan gt);

	public T visit(SmallerOrEqual leq);

	public T visit(SmallerThan lt);

	public T visit(Mul mul);

	public T visit(NotEqual neq);

	public T visit(Negative neg);

	public T visit(NOT not);

	public T visit(OR or);

	public T visit(Positive pos);

	public T visit(Sub sub);

	public T visit(IntegerLiteral intLiteral);

	public T visit(BooleanLiteral boolLiteral);

	public T visit(StringLiteral stringLiteral);
	
	public T visit(MoneyLiteral moneyLiteral);
	
	public T visit(VarExpr varExpr);


}
