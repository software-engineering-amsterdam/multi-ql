package org.uva.sea.ql.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
import org.uva.sea.ql.ast.type.IntType;
import org.uva.sea.ql.ast.type.StrType;
import org.uva.sea.ql.ast.type.TypeVisitor;
import org.uva.sea.ql.ast.type.UndefinedType;
import org.uva.sea.ql.gui.panels.Panel;
import org.uva.sea.ql.gui.widgets.Widget;



public class QlGUIBuilder implements FormVisitor, StatementVisitor, TypeVisitor {
	
	private JFrame frame;
	private List<Panel> panels;
	
	public QlGUIBuilder(Form form) {
		panels = new ArrayList<Panel>();
		frame = new JFrame("Questionnaire");
		JLabel l=new JLabel("Questionnaire");
		frame.add(l);
		frame.setSize(400,400);
		//frame.setVisible(true);
		
		visitForm(form);
	}

	public JFrame getFrame() {
		return frame;
	}

	public List<Panel> getPanels() {
		return panels;
	}
	
	 public void addPanel(Panel panel) {
		 panels.add(panel);
	 }

	@Override
	public void visitForm(Form form) {
		// listener goes here i guess . . .
		form.getBlock().accept(this);
		System.out.println("GUI ready . . . ");
		
		setFrameVisible(true);
	}


	@Override
	public void visitBlock(Block block) {
		for (Statement statement: block.getStatements()) {
			statement.accept(this);
		}	
		System.out.println("Visiting block");
		
	}
	
		
	private void setFrameVisible(boolean visibility) {
		frame.setVisible(visibility);
	}

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget visit(IntType intType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget visit(BoolType boolType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget visit(StrType strType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget visit(UndefinedType undefinedType) {
		// TODO Auto-generated method stub
		return null;
	}

}
