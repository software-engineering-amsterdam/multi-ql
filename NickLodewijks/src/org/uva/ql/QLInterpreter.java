package org.uva.ql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Context.ContextListener;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.stat.IFStat;

public class QLInterpreter extends ASTNodeVisitorAdapter {

	private JFrame jframe = new JFrame("test");
	private Context context = new Context();

	private JPanel form;

	public QLInterpreter() {
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

	private void addContextListener(ContextListener listener) {
		context.addContextListener(listener);
	}

	private JPanel createPanel(Block block) {
		JPanel blockPanel;

		blockPanel = new JPanel();
		blockPanel.setLayout(new BoxLayout(blockPanel, BoxLayout.PAGE_AXIS));

		for (Question q : block.getQuestions()) {
			blockPanel.add(new QuestionImpl(q));
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

		addContextListener(new ContextListener() {
			@Override
			public void contextChanged(Context context) {
				panel.setVisible(ifStat.interpret(context));
			}
		});

		return panel;
	}

	private class QuestionImpl extends JPanel {
		private String variableId;
		private JLabel label;
		private JTextField textField;
		private JRadioButton rbYes;
		private JRadioButton rbNo;

		public QuestionImpl(Question q) {
			ButtonGroup bg;

			variableId = q.getVariableId().getName();

			setSize(100, 40);

			label = new JLabel(q.getLabel());

			add(label);

			switch (q.getVariableId().getType()) {
			case BOOLEAN:
				bg = new ButtonGroup();
				rbYes = new JRadioButton("Yes");
				rbYes.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						context.setValue(variableId, true);
					}
				});

				rbNo = new JRadioButton("No");
				rbNo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						context.setValue(variableId, false);
					}
				});

				bg.add(rbYes);
				bg.add(rbNo);

				add(rbYes);
				add(rbNo);
				break;
			case INTEGER:
			case STRING:
				textField = new JTextField();
				textField.setSize(100, 30);
				textField.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						context.setValue(variableId, textField.getText());
					}
				});
				add(textField);
				break;
			default:
				break;
			}
		}

	}
}
