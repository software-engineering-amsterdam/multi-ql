package org.uva.ql.ast;

import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.UnaryExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.expr.math.Divide;
import org.uva.ql.ast.expr.math.Multiply;
import org.uva.ql.ast.expr.math.Negative;
import org.uva.ql.ast.expr.math.Positive;
import org.uva.ql.ast.expr.math.Subtract;
import org.uva.ql.ast.expr.rel.And;
import org.uva.ql.ast.expr.rel.Equals;
import org.uva.ql.ast.expr.rel.GreaterThanOrEquals;
import org.uva.ql.ast.expr.rel.GreaterThan;
import org.uva.ql.ast.expr.rel.LessThanOrEquals;
import org.uva.ql.ast.expr.rel.LessThan;
import org.uva.ql.ast.expr.rel.EqualsNot;
import org.uva.ql.ast.expr.rel.Not;
import org.uva.ql.ast.expr.rel.Or;
import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.stat.QLQuestion;

public interface ASTNodeVisitor<T, U> {

	public T visit(ASTNode node, U context);

	public T visit(Expr node, U context);

	public T visit(BinaryExpr node, U context);

	public T visit(UnaryExpr node, U context);

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

	public T visit(QLQuestionnaire node, U context);

	public T visit(QLForm node, U context);

	public T visit(QLBlock node, U context);

	public T visit(QLIFStatement node, U context);

	public T visit(BooleanLiteral node, U context);

	public T visit(IntegerLiteral node, U context);

	public T visit(StringLiteral node, U context);

	public T visit(QLQuestion node, U context);

	public T visit(QLQuestionInput node, U context);

	public T visit(QLQuestionComputed node, U context);
}
