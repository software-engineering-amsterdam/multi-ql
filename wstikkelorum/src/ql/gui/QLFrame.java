package ql.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ql.QLdrawer;
import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.GuiVisitor;

public class QLFrame extends JFrame {
	private JFrame mainFrame;
	private JPanel controlPanel;
	private List<UIElement> visibleQuestions;
	private GuiVisitor<Object> guiVisitor;
	private final Form form;
	private static final long serialVersionUID = 1L;

	public QLFrame(Form form, Context context, QLdrawer parent) {
		this.form = form;

		guiVisitor = new GuiVisitor<>(context, parent);
		guiVisitor.visit(form);
		visibleQuestions = guiVisitor.getVisibleQuestions();

		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setSize(600, 800);

		controlPanel = new JPanel();
		mainFrame.add(controlPanel);

		drawVisibleQuestions();
	}

	public void updateQLFrame(Context newContext) {
		visibleQuestions.clear();
		guiVisitor.setNewContext(newContext);
		guiVisitor.visit(form);
		visibleQuestions = guiVisitor.getVisibleQuestions();
		drawVisibleQuestions();
	}

	private void drawVisibleQuestions() {
		controlPanel.removeAll();
		controlPanel.setLayout(new GridLayout(visibleQuestions.size(), 1));

		for (UIElement element : visibleQuestions) {
			controlPanel.add(element.getPanel());
		}

		controlPanel.revalidate();
		controlPanel.repaint();
	}
}
