package org.uva.sea.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Block extends ASTNode implements Visitable {
	List<Stat> stmts;
	
	public Block() {
		stmts = new ArrayList<Stat>();
	}
	
	public List<Stat> getStmts() {
		return stmts;
	}
	
	public void add(Stat stmt) {
		stmts.add(stmt);
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "Block";
	}

}
