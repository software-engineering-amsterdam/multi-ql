package org.uva.sea.ql.ast;

public class IFBLOCK {
	private final EXPRESSION condition;
	private final Block body;

	public IFBLOCK(EXPRESSION condition, Block body) {
		this.condition = condition;
		this.body = body;
	}
}
