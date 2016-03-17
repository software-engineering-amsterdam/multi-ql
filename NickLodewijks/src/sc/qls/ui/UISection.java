package sc.qls.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import sc.ql.ui.UIQuestion;
import sc.qls.ast.Section;

public class UISection {

	private final List<UIQuestion> questions;
	private final Section section;
	private final JPanel panel;

	public UISection(Section section, List<UIQuestion> questions) {
		this.section = section;
		this.questions = sort(questions);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(createBorder());

		for (UIQuestion question : this.questions) {
			panel.add(question.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}
	}

	private List<UIQuestion> sort(List<UIQuestion> questions) {
		List<UIQuestion> sortedQuestions;

		sortedQuestions = new ArrayList<>(questions);
		sortedQuestions.sort((q1, q2) -> {
			return Integer.compare(section.indexOf(q1.getName()), section.indexOf(q2.getName()));
		});

		return sortedQuestions;
	}

	private Border createBorder() {
		TitledBorder title;

		title = BorderFactory.createTitledBorder(section.name());
		title.setTitleJustification(TitledBorder.LEFT);

		return title;
	}

	public JComponent getComponent() {
		return panel;
	}
}