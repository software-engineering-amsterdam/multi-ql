package sc.qls.ui;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import sc.ql.ui.UIQuestion;
import sc.qls.ast.Section;

public class UISection {

	private final List<UIQuestion> questions;
	private JPanel panel;

	public UISection(Section section, List<UIQuestion> questions) {
		TitledBorder title;

		this.questions = questions;
		Collections.sort(this.questions, (o1, o2) -> {
			return Integer.compare(section.indexOf(o1.getId()), section.indexOf(o2.getId()));
		});

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		title = BorderFactory.createTitledBorder(section.name());
		title.setTitleJustification(TitledBorder.LEFT);
		panel.setBorder(title);

		for (UIQuestion question : questions) {
			panel.add(question.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}
	}

	public JComponent getComponent() {
		return panel;
	}
}