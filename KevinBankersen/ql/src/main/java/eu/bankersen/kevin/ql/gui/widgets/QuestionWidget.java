package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.ViewListener;
import eu.bankersen.kevin.ql.gui.widgets.input.InputWidget;
import eu.bankersen.kevin.ql.interperter.DataListener;

public class QuestionWidget implements Widget, DataListener {

    private final String name;
    private final Boolean computed;
    private final QLType type;
    private final JPanel questionPanel;
    private final InputWidget questionInput;
    private final List<ViewListener> viewListeners;

    public QuestionWidget(String name, String question, Boolean isComputed, QLType type) {

	viewListeners = new ArrayList<>();
	this.name = name;
	this.computed = isComputed;
	this.type = type;

	questionPanel = new JPanel(new BorderLayout());
	questionPanel.setVisible(true);

	String questionString = question;
	JLabel questionText = new JLabel("<html><p>" + questionString + "</p></html>");
	int rows = (questionString.length() / 40);
	questionText.setPreferredSize(new Dimension(290, rows * 20));

	questionPanel.add(questionText, BorderLayout.WEST);

	questionInput = type.defaultWidget();
	questionInput.setParent(this);

	questionPanel.add(questionInput.build(), BorderLayout.EAST);
    }

    @Override
    public JPanel build() {
	return questionPanel;
    }

    @Override
    public boolean isComputed() {
	return computed;
    }

    @Override
    public void dataUpdate(Map<String, QLValue> context) {
	if (context.containsKey(name)) {
	    questionPanel.setVisible(true);
	    questionInput.updateWidgetValue(context.get(name));
	} else {
	    questionInput.updateWidgetValue(new UndifinedValue());
	    questionPanel.setVisible(false);
	}

	// Update the view.
	QuestionWidget.this.questionPanel.revalidate();
	QuestionWidget.this.questionPanel.repaint();
    }

    @Override
    public void widgetUpdated(String value) {
	viewListeners.forEach(l -> l.viewUpdate(name, type.createQLValueFrom(value)));
    }

    public void addUIListener(ViewListener listener) {
	viewListeners.add(listener);
    }

}
