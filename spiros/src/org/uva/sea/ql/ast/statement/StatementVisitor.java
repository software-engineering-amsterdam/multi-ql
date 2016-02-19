package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.block.Block;

public interface StatementVisitor<T> {

	public T visit(Block block);
	public T visit(ComputedQuestion computedQuestion);
	public T visit(Question question);
	public T visit(IfStatement ifStatement);
	public T visit(IfElseStatement ifElseStatement);
	
}


