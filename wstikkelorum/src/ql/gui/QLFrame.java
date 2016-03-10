package ql.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.GuiVisitor;

public class QLFrame extends JFrame{
	private JFrame mainFrame;
	private JPanel controlPanel;
	private List<UIElement> visibleQuestions;
	private GuiVisitor<Object> guiVisitor;
	private static final long serialVersionUID = 1L;

	public QLFrame(Form form, Context context){
		guiVisitor = new GuiVisitor<>(context);
		guiVisitor.visit(form);
		visibleQuestions = guiVisitor.getVisibleQuestions();
		drawVisibleQuestions();
	}
	
	private void drawVisibleQuestions(){
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(visibleQuestions.size(), 1));
		
		for(UIElement element : visibleQuestions){
			controlPanel.add(element.getPanel());
		}	
		
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		mainFrame.setSize(600, 800);
	}
}
