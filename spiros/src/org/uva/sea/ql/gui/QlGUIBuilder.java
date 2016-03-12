package org.uva.sea.ql.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.FormVisitor;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.Statement;
import org.uva.sea.ql.ast.statement.StatementVisitor;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.gui.panels.Panel;
import org.uva.sea.ql.gui.panels.QuestionPanel;
import org.uva.sea.ql.gui.widgets.CheckBox;
import org.uva.sea.ql.gui.widgets.TextField;
import org.uva.sea.ql.gui.widgets.Widget;


// typevisitor not used so far . . .


public class QlGUIBuilder implements FormVisitor, StatementVisitor {
	
	private JFrame frame;
	private List<JPanel> panels;
	
	public QlGUIBuilder(Form form) {
		panels = new ArrayList<JPanel>();
		visitForm(form);
		
		
		frame = new JFrame("Questionnaire");
		frame.setLayout(new MigLayout("al left, wrap, gapy 66"));
		
		System.out.println("To size twn panels einai: " + panels.size());
		
		for (JPanel panel: panels)
			frame.add(panel);
			
		frame.setSize(550,550);
		frame.setVisible(true);
		
	}

	public JFrame getFrame() {
		return frame;
	}

//	public List<Panel> getPanels() {
//		return panels;
//	}
	
	 public void addPanel(Panel panel) {
		 panels.add(panel);
	 }

	@Override
	public void visitForm(Form form) {
		// listener goes here i guess . . .
		form.getBlock().accept(this);
		System.out.println("GUI ready . . . ");
		
		//setFrameVisible(true);
	}


	@Override
	public void visitBlock(Block block) {
		
		System.out.println("Visiting block");
		
		for (Statement statement: block.getStatements()) {
			statement.accept(this);
		}	
		
	}
	
		

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		
		System.out.println("Visiting computed question...");
		
		Type type = computedQuestion.getType();
		Widget widget = widgetAccordingToType(type);
		QuestionPanel questionPanel = new QuestionPanel(computedQuestion,widget);
		panels.add(questionPanel.getPanel());
	}

	@Override
	public void visitQuestion(Question question) {
		
		System.out.println("Visiting question...");
		
		Type type = question.getType();
		Widget widget = widgetAccordingToType(type);
		QuestionPanel questionPanel = new QuestionPanel(question,widget);
		panels.add(questionPanel.getPanel());
		
	}

	private Widget widgetAccordingToType(Type type) {
		if (type instanceof BoolType)
			return new CheckBox();
		else
			return new TextField();
	}

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Widget visit(IntType intType) {
//		return new TextField();
//	}
//
//	@Override
//	public Widget visit(BoolType boolType) {
//		return new CheckBox();
//	}
//
//	@Override
//	public Widget visit(StrType strType) {
//		return new TextField();
//	}
//
//	@Override
//	public Widget visit(UndefinedType undefinedType) {
//		return null;
//	}

}