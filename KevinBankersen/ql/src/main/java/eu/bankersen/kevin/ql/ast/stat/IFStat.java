package eu.bankersen.kevin.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Block;
import eu.bankersen.kevin.ql.ast.form.Question;
import eu.bankersen.kevin.ql.ast.var.Type;

public class IFStat {
	
	private final Expr expr;
	private final Block body;

	public IFStat(Expr expression, Block body) {
		this.expr = expression;
		this.body = body;
	}
	
	public Boolean checkType(){
		
		return body.checkType() && expr.getType() == Type.BOOLEAN;
	}
	
	public Block getBody(){
		return body;
	}
	
	@Override
	public String toString(){
		return body.toString();
	}
}
