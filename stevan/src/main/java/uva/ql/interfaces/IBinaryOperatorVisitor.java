package uva.ql.interfaces;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.AbstractLogicalOperator;
import uva.ql.ast.expressions.abstracts.AbstractRelationalOperator;
import uva.ql.ast.expressions.abstracts.AbstractSingleLogicalOperator;

public interface IBinaryOperatorVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	
	public void visitCondIfElseStatement(CondIfElseStatement condition);
	public void visitCondIfStatement(CondIfStatement condition);

	public void visitLogicalOperator(AbstractLogicalOperator exp);
	public void visitRelationalOperator(AbstractRelationalOperator exp);
	public void visitSingleLogicalOperator(AbstractSingleLogicalOperator exp);
}
