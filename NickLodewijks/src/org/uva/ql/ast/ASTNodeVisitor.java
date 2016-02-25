package org.uva.ql.ast;

import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.UnaryExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.expr.math.Div;
import org.uva.ql.ast.expr.math.Mul;
import org.uva.ql.ast.expr.math.Neg;
import org.uva.ql.ast.expr.math.Pos;
import org.uva.ql.ast.expr.math.Sub;
import org.uva.ql.ast.expr.rel.And;
import org.uva.ql.ast.expr.rel.Eq;
import org.uva.ql.ast.expr.rel.GEq;
import org.uva.ql.ast.expr.rel.GT;
import org.uva.ql.ast.expr.rel.LEq;
import org.uva.ql.ast.expr.rel.LT;
import org.uva.ql.ast.expr.rel.NEq;
import org.uva.ql.ast.expr.rel.Not;
import org.uva.ql.ast.expr.rel.Or;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.ComputedQuestion;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ast.stat.InputQuestion;
import org.uva.ql.ast.stat.Question;

public interface ASTNodeVisitor<T, U> {

	public T visit(ASTNode node, U context);

	public T visit(Expr node, U context);

	public T visit(BinaryExpr node, U context);

	public T visit(UnaryExpr node, U context);

	public T visit(Add node, U context);

	public T visit(Sub node, U context);

	public T visit(Div node, U context);

	public T visit(Mul node, U context);

	public T visit(Eq node, U context);

	public T visit(GEq node, U context);

	public T visit(GT node, U context);

	public T visit(LEq node, U context);

	public T visit(LT node, U context);

	public T visit(NEq node, U context);

	public T visit(Or node, U context);

	public T visit(And node, U context);

	public T visit(LiteralExpr node, U context);

	public T visit(Neg node, U context);

	public T visit(Not node, U context);

	public T visit(Pos node, U context);

	public T visit(VariableExpr node, U context);

	public T visit(Questionnaire node, U context);

	public T visit(Form node, U context);

	public T visit(Block node, U context);

	public T visit(IFStat node, U context);

	public T visit(BooleanLiteral node, U context);

	public T visit(IntegerLiteral node, U context);

	public T visit(StringLiteral node, U context);

	public T visit(Question node, U context);

	public T visit(InputQuestion node, U context);

	public T visit(ComputedQuestion node, U context);
}
