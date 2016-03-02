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
	
	@Override
	public boolean equals(Object ob) {
		Pair p = (Pair) ob;
		return p.getLeft().equals(left) && p.getRight().equals(right);
	}
	
	@Override
	public int hashCode(){
	    return left.hashCode() + right.hashCode();
    }
	
}