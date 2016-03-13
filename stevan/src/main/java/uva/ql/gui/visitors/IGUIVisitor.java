package uva.ql.gui.visitors;

import javax.swing.JComponent;
import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.variables.abstracts.Variable;

public interface IGUIVisitor {

	public void visitForm(Form form);
	public JComponent visitBlock(Block block);
	
	public void visitQuestionVanilla(QuestionVanilla question, JPanel panel);
	public void visitQuestionComputed(QuestionComputed question, JPanel panel);
	
	public void visitCondIfStatement(CondIfStatement condition, JPanel panel);
	public void visitCondIfElseStatement(CondIfElseStatement condition, JPanel panel);
	
	public void visitVar(Variable variable);
	public void visitNum(Values values);
}
