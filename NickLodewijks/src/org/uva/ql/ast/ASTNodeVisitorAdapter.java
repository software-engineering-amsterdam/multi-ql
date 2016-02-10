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

public class ASTNodeVisitorAdapter implements ASTNodeVisitor {

	@Override
	public void visit(ASTNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BinaryExpr node) {
		visitChildren(node);
	}

	@Override
	public void visit(ArithmeticExpr node) {
		visitChildren(node);
	}

	@Override
	public void visit(Add node) {
		visitChildren(node);
	}

	@Override
	public void visit(Sub node) {
		visitChildren(node);
	}

	@Override
	public void visit(Div node) {
		visitChildren(node);
	}

	@Override
	public void visit(Mul node) {
		visitChildren(node);
	}

	private void visitChildren(BinaryExpr node) {
		node.left().accept(this);
		node.right().accept(this);
	}

	@Override
	public void visit(Eq node) {
		visitChildren(node);
	}

	@Override
	public void visit(GEq node) {
		visitChildren(node);
	}

	@Override
	public void visit(GT node) {
		visitChildren(node);
	}

	@Override
	public void visit(LEq node) {
		visitChildren(node);
	}

	@Override
	public void visit(LT node) {
		visitChildren(node);
	}

	@Override
	public void visit(NEq node) {
		visitChildren(node);
	}

	@Override
	public void visit(And node) {
		visitChildren(node);
	}

	@Override
	public void visit(Or node) {
		visitChildren(node);
	}

	@Override
	public void visit(LiteralExpr node) {
		node.getLiteral().accept(this);
	}

	@Override
	public void visit(Neg node) {
		node.getExpr().accept(this);
	}

	@Override
	public void visit(Not node) {
		node.getExpr().accept(this);
	}

	@Override
	public void visit(Pos node) {
		node.getExpr().accept(this);
	}

	@Override
	public void visit(VariableExpr node) {
		node.getVariableId().accept(this);
	}

	@Override
	public void visit(Form node) {
		node.getBody().accept(this);
	}

	@Override
	public void visit(Block node) {
		for (VariableDecl variable : node.getVariables()) {
			variable.accept(this);
		}

		for (Question q : node.getQuestions()) {
			q.accept(this);
		}

		for (IFStat statement : node.getIfStatements()) {
			statement.accept(this);
		}
	}

	@Override
	public void visit(IFStat node) {
		node.getExpression().accept(this);
		node.getBody().accept(this);
	}

	@Override
	public void visit(BooleanLiteral node) {
		// do nothing
	}

	@Override
	public void visit(IntegerLiteral node) {
		// do nothing
	}

	@Override
	public void visit(StringLiteral node) {
		// do nothing
	}

	@Override
	public void visit(InputQuestion node) {
		node.getVariableId().accept(this);
	}

	@Override
	public void visit(VariableDecl node) {
		node.getType().accept(this);
		node.getId().accept(this);
	}

	@Override
	public void visit(VariableIdentifier node) {
		// do nothing
	}

	@Override
	public void visit(ComputedQuestion node) {
		node.getVariableId().accept(this);
		node.getExpression().accept(this);
	}
}
