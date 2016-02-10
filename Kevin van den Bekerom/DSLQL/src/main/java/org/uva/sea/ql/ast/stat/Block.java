package org.uva.sea.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTID;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitor;

public class Block extends ASTNode {
	List<Stat> stmts;
	
	public Block() {
		super(ASTID.BLOCK);
		stmts = new ArrayList<Stat>();
	}
	
	public List<Stat> getStmts() {
		return stmts;
	}

	public void set(List<Stat> stmts) {
		this.stmts = stmts;
	}
	
	public void add(Stat stmt) {
		stmts.add(stmt);
	}

	public void accept(Visitor visitor) {
		for (Stat stmt : stmts) {
			stmt.accept(visitor);
		}
		visitor.visit(this);
	}

}
