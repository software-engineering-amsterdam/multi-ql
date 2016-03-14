package uva.ql.visitors;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.gui.Question;
import uva.ql.gui.visitors.IGUIVisitor;

public class VisitorASTToGUI implements IGUIVisitor {

	private final Map<String, JComponent> componentStore = new HashMap<String, JComponent>(0);
	
	public VisitorASTToGUI() {
	}
	
	@Override
	public void visitForm(Form form, JPanel parentPanel) {
		
		for(int i=0; i<form.size(); i++) {
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.setPreferredSize(new Dimension(parentPanel.getParent().getWidth()-30, parentPanel.getParent().getHeight()-30));

			parentPanel.add(panel);
			parentPanel.revalidate();
			
			Block block = (Block) form.get(i);
			block.accept(this, panel);
		}
	}

	@Override
	public void visitBlock(Block block, JPanel parentPanel) {
		
		for(int i=0; i<block.size(); i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			parentPanel.add(panel);
			parentPanel.revalidate();
			
			block.get(i).accept(this, panel);
		}
	}

	@Override
	public void visitQuestionVanilla(QuestionVanilla question, JPanel parentPanel) {
		Variable var = question.getVariable();
		Question q = new Question(question.getLabel(), var);
		
		//q.setPreferredSize(new Dimension(parentPanel.getWidth()-30, 20));
		parentPanel.add(q);
		parentPanel.revalidate();
		
		JComponent component = (JComponent) q.getComponent(1);
		componentStore.put(component.getName(), component);
	}

	@Override
	public void visitQuestionComputed(QuestionComputed question, JPanel parentPanel) {
		Variable var = question.getVariable();
		Question q = new Question(question.getLabel(), var);
		
		//q.setPreferredSize(new Dimension(parentPanel.getWidth()-30, 20));
		parentPanel.add(q);
		parentPanel.revalidate();
		
		JComponent component = (JComponent) q.getComponent(1);
		componentStore.put(component.getName(), component);
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condition, JPanel parentPanel) {
		
		JPanel panelLhs = new JPanel();
		panelLhs.setLayout(new BoxLayout(panelLhs, BoxLayout.PAGE_AXIS));
		
		condition.getLhs().accept(this, panelLhs);
		parentPanel.add(panelLhs);
		parentPanel.revalidate();
	}

	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condition, JPanel parentPanel) {
		
		JPanel panelLhs = new JPanel();
		panelLhs.setLayout(new BoxLayout(panelLhs, BoxLayout.PAGE_AXIS));
		condition.getLhs().accept(this, panelLhs);
		parentPanel.add(panelLhs);
		
		JPanel panelRhs = new JPanel();
		panelRhs.setLayout(new BoxLayout(panelRhs, BoxLayout.PAGE_AXIS));
		condition.getRhs().accept(this, panelRhs);
		parentPanel.add(panelRhs);
		
		parentPanel.revalidate();
	}
}
