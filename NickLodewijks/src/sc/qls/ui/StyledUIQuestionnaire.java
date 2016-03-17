package sc.qls.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sc.ql.ui.UIQuestion;
import sc.ql.ui.UIQuestionnaire;
import sc.qls.ast.Page;
import sc.qls.ast.StyleSheet;

public class StyledUIQuestionnaire extends UIQuestionnaire {

	private final List<UIPage> pages = new ArrayList<>();

	public StyledUIQuestionnaire(List<UIQuestion> questions, StyleSheet styleSheet) {
		super(questions);

		for (Page page : styleSheet.getPages()) {
			List<UIQuestion> questionsOnPage;

			questionsOnPage = new ArrayList<>();

			for (UIQuestion question : questions) {
				if (page.containsQuestions(question.getId())) {
					questionsOnPage.add(question);
				}
			}

			pages.add(new UIPage(page, questionsOnPage));
		}
	}

	@Override
	public void show() {
		JPanel panel;
		JPanel root;
		JFrame jframe;
		JScrollPane scrollPanel;
		JPanel formPanel;

		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
		for (UIPage page : pages) {
			formPanel.add(page.getComponent());
			formPanel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

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

		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jframe.setContentPane(root);
		jframe.setSize(450, 600);
		jframe.setLocationRelativeTo(null);

		jframe.setVisible(true);
	}
}