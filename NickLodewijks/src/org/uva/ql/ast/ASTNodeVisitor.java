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
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.IFStat;

public interface ASTNodeVisitor {

	public void visit(ASTNode node);

	public void visit(BinaryExpr node);

	public void visit(ArithmeticExpr node);

	public void visit(Add node);

	public void visit(Sub node);

	public void visit(Div node);

	public void visit(Mul node);

	public void visit(Eq node);

	public void visit(GEq node);

	public void visit(GT node);

	public void visit(LEq node);

	public void visit(LT node);

	public void visit(NEq node);

	public void visit(Or node);

	public void visit(And node);

	public void visit(LiteralExpr node);

	public void visit(Neg node);

	public void visit(Not node);

	public void visit(Pos node);

	public void visit(VariableExpr node);

	public void visit(Form node);

	public void visit(Block node);

	public void visit(IFStat node);

	public void visit(BooleanLiteral node);

	public void visit(IntegerLiteral node);

	public void visit(StringLiteral node);

	public void visit(VariableDecl node);

	public void visit(VariableIdentifier node);

	public void visit(InputQuestion node);

	public void visit(ComputedQuestion node);

}
