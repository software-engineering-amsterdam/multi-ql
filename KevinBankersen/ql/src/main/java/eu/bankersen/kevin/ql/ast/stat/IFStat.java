package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Block;

public class IFStat {
	
	private final Expr expr;
	private final Block body;

	public IFStat(Expr expression, Block body) {
		this.expr = expression;
		this.body = body;
	}
	
	public Block getBody(){
		return body;
	}

}
