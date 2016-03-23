package sc.ql.ui;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UIQuestionnaire {

	private final List<UIQuestion> questions;

	public UIQuestionnaire(List<UIQuestion> questions) {
		this.questions = questions;
	}

	public List<UIQuestion> questions() {
		return Collections.unmodifiableList(questions);
	}

	public void show() {
		JPanel panel;
		JPanel root;
		JFrame jframe;
		JScrollPane scrollPanel;
		JPanel formPanel;

		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));

		scrollPanel = new JScrollPane();
		scrollPanel.setViewportView(formPanel);
		scrollPanel.setBorder(null);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(scrollPanel);

		root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.X_AXIS));

		root.add(Box.createGlue());
		root.add(panel);
		root.add(Box.createGlue());

		jframe.setContentPane(root);
		jframe.setSize(450, 600);
		jframe.setLocationRelativeTo(null);

		for (UIQuestion question : questions) {
			JComponent qPanel;

			qPanel = question.getComponent();

			formPanel.add(qPanel);
			formPanel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		jframe.setVisible(true);
	}
}