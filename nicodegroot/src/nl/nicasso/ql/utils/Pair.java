package nl.nicasso.ql.utils;

import nl.nicasso.ql.ast.literal.IdentifierLit;

public class Pair {

	IdentifierLit left;
	IdentifierLit right;
	
	public Pair(IdentifierLit left, IdentifierLit right) {
		super();
		this.left = left;
		this.right = right;
	}

	public IdentifierLit getLeft() {
		return left;
	}

	public IdentifierLit getRight() {
		return right;
	}
	
}