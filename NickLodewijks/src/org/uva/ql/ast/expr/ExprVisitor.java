package org.uva.ql.ast.expr;

import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.expr.math.Divide;
import org.uva.ql.ast.expr.math.Multiply;
import org.uva.ql.ast.expr.math.Negative;
import org.uva.ql.ast.expr.math.Positive;
import org.uva.ql.ast.expr.math.Subtract;
import org.uva.ql.ast.expr.rel.And;
import org.uva.ql.ast.expr.rel.Equals;
import org.uva.ql.ast.expr.rel.EqualsNot;
import org.uva.ql.ast.expr.rel.GreaterThan;
import org.uva.ql.ast.expr.rel.GreaterThanOrEquals;
import org.uva.ql.ast.expr.rel.LessThan;
import org.uva.ql.ast.expr.rel.LessThanOrEquals;
import org.uva.ql.ast.expr.rel.Not;
import org.uva.ql.ast.expr.rel.Or;

public interface ExprVisitor<T, U> {

	public T visit(Add node, U context);

	public T visit(Subtract node, U context);

	public T visit(Divide node, U context);

	public T visit(Multiply node, U context);

	public T visit(Equals node, U context);

	public T visit(GreaterThanOrEquals node, U context);

	public T visit(GreaterThan node, U context);

	public T visit(LessThanOrEquals node, U context);

	public T visit(LessThan node, U context);

	public T visit(EqualsNot node, U context);

	public T visit(Or node, U context);

	public T visit(And node, U context);

	public T visit(LiteralExpr node, U context);

	public T visit(Negative node, U context);

	public T visit(Not node, U context);

	public T visit(Positive node, U context);

	public T visit(VariableExpr node, U context);

}
