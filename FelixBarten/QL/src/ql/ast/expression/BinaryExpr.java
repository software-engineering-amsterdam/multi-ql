package ql.ast.expression;

import ql.ast.Expr;

public class BinaryExpr extends Expr {
	private Expr left;
	private Expr right;
	
	public BinaryExpr (Expr lhs, Expr rhs) {
		this.setLeft(lhs);
		this.setRight(rhs);
	}

	public Expr getLeft() {
		return left;
	}

	public void setLeft(Expr left) {
		this.left = left;
	}

	public Expr getRight() {
		return right;
	}

	public void setRight(Expr right) {
		this.right = right;
	}
	
}
