package org.uva.ql;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Context.ContextListener;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ui.DefaultWidgetFactory;
import org.uva.ql.ui.QLQuestion;
import org.uva.ql.ui.WidgetFactory;

public class QLInterpreter extends ASTNodeVisitorAdapter {

	private JFrame jframe = new JFrame("test");
	private Context context = new Context();

	private WidgetFactory widgetFactory;

	private JPanel form;

	public QLInterpreter() {
		widgetFactory = new DefaultWidgetFactory();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return jframe;
	}

	@Override
	public void visit(Form node) {
		form = new JPanel();
		jframe.add(form);

		form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));

		form.add(createPanel(node.getBody()));
		form.repaint();
	}

	private JPanel createPanel(Block block) {
		JPanel blockPanel;

		blockPanel = new JPanel();
		blockPanel.setLayout(new BoxLayout(blockPanel, BoxLayout.PAGE_AXIS));

		for (Question q : block.getQuestions()) {
			JPanel panel;
			QLQuestion uiQuestion;

			uiQuestion = q.getUIComponent(widgetFactory);
			uiQuestion.setContext(context);

			panel = new JPanel();
			panel.setSize(100, 40);
			panel.add(uiQuestion.getLabelComponent());
			panel.add(uiQuestion.getInputComponent());
			blockPanel.add(panel);
		}

		for (IFStat statement : block.getIfStatements()) {
			blockPanel.add(createPanel(statement));
		}

		return blockPanel;
	}

	private JPanel createPanel(IFStat ifStat) {
		JPanel panel;

		panel = createPanel(ifStat.getBody());
		panel.setVisible(ifStat.interpret(context));

		context.addContextListener(new ContextListener() {
			@Override
			public void contextChanged(Context context) {
				boolean newValue;

				newValue = ifStat.interpret(context);
				if (panel.isVisible() != newValue) {
					panel.setVisible(newValue);
					jframe.pack();
				}
			}
		});

		return panel;
	}
}
