package uva.ql.gui.visitors;

import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;

public interface IActionListenerVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	
	public void visitCondIfStatement(CondIfStatement condition);
	public void visitCondIfElseStatement(CondIfElseStatement condition);
		
	public void visitLogicalOperatorBinary(LogicalOperatorBinary exp);
}
