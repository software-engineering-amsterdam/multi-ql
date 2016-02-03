package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Block;

public class IFStat {
	private final Expr expression;
	private final Block body;

	public IFStat(Expr expression, Block body) {
		this.expression = expression;
		this.body = body;
	}
}
