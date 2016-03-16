package uva.ql.gui.visitors;

import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;

public interface IGUIVisitor {

	public void visitForm(Form form, JPanel panel);
	public void visitBlock(Block block, JPanel panel);
	
	public void visitQuestionVanilla(QuestionVanilla question, JPanel panel);
	public void visitQuestionComputed(QuestionComputed question, JPanel panel);
	
	public void visitCondIfStatement(CondIfStatement condition, JPanel panel);
	public void visitCondIfElseStatement(CondIfElseStatement condition, JPanel panel);
	
}
