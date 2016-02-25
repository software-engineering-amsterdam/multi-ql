package nl.nicasso.ql.utils;

import nl.nicasso.ql.ast.expression.Identifier;

public class Pair {

	Identifier left;
	Identifier right;
	
	public Pair(Identifier left, Identifier right) {
		super();
		this.left = left;
		this.right = right;
	}

	public Identifier getLeft() {
		return left;
	}

	public Identifier getRight() {
		return right;
	}
	
}