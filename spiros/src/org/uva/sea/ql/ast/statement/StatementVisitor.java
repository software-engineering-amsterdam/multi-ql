package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.block.Block;

public interface StatementVisitor<T> {

	public T visitBlock(Block block);
	public T visitComputedQuestion(ComputedQuestion computedQuestion);
	public T visitQuestion(Question question);
	public T visitIfStatement(IfStatement ifStatement);
	public T visitIfElseStatement(IfElseStatement ifElseStatement);
	
}


