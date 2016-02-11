package org.uva.ql.ast;

import org.uva.ql.ast.expr.Add;
import org.uva.ql.ast.expr.And;
import org.uva.ql.ast.expr.ArithmeticExpr;
import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Div;
import org.uva.ql.ast.expr.Eq;
import org.uva.ql.ast.expr.GEq;
import org.uva.ql.ast.expr.GT;
import org.uva.ql.ast.expr.LEq;
import org.uva.ql.ast.expr.LT;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.Mul;
import org.uva.ql.ast.expr.NEq;
import org.uva.ql.ast.expr.Neg;
import org.uva.ql.ast.expr.Not;
import org.uva.ql.ast.expr.Or;
import org.uva.ql.ast.expr.Pos;
import org.uva.ql.ast.expr.Sub;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.IFStat;

public interface ASTNodeVisitor<T, U> {

	public T visit(ASTNode node, U context);

	public T visit(BinaryExpr node, U context);

	public T visit(ArithmeticExpr node, U context);

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

	public T visit(VariableDecl node, U context);

	public T visit(VariableIdentifier node, U context);

	public T visit(InputQuestion node, U context);

	public T visit(ComputedQuestion node, U context);
}
