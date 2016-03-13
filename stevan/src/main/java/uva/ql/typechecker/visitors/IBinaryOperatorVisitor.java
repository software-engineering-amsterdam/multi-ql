package uva.ql.typechecker.visitors;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;

public interface IBinaryOperatorVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	
	public void visitCondIfElseStatement(CondIfElseStatement condition);
	public void visitCondIfStatement(CondIfStatement condition);

	public void visitLogicalOperator(LogicalOperatorBinary exp);
	public void visitRelationalOperator(RelationalOperatorBinary exp);
	public void visitSingleLogicalOperator(LogicalOperatorUnary exp);
}
