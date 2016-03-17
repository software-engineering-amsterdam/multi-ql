package sc.qls.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import sc.ql.ui.UIQuestion;
import sc.qls.ast.Page;
import sc.qls.ast.Section;

public class UIPage {

	private final List<UISection> sections = new ArrayList<>();
	private JPanel panel;

	public UIPage(Page page, List<UIQuestion> questions) {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		for (Section section : page.sections()) {
			List<UIQuestion> questionsInSection;
			UISection uiSection;

			questionsInSection = questions.stream().filter(q -> section.contains(q.getId()))
					.collect(Collectors.toList());

			uiSection = new UISection(section, questionsInSection);
			sections.add(uiSection);

			panel.add(uiSection.getComponent());
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}
	}

	public JComponent getComponent() {
		return panel;
	}
}