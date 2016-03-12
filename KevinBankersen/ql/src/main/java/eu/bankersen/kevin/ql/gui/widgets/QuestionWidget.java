package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.object.type.QLType;
import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.gui.ViewListener;
import eu.bankersen.kevin.ql.interpreter.DataListener;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class QuestionWidget implements Widget, DataListener {

    private final String name;
    private final JPanel questionContainer;
    private final InputWidget questionInput;
    private final List<ViewListener> viewListeners;
    private final List<InputWidget> widgetListeners;

    public QuestionWidget(String name, String question, Boolean isComputed, QLType type) {

	viewListeners = new ArrayList<>();
	widgetListeners = new ArrayList<>();
	this.name = name;

	questionContainer = new JPanel(new BorderLayout());
	questionContainer.setVisible(true);

	String questionString = question;
	JLabel questionText = new JLabel("<html><p>" + questionString + "</p></html>");
	int rows = (questionString.length() / 40);
	questionText.setPreferredSize(new Dimension(290, rows * 20));

	questionContainer.add(questionText, BorderLayout.WEST);

	questionInput = type.defaultWidget();
	questionInput.setComputed(isComputed);

	questionInput.addWidgetListener(this);
	questionContainer.add(questionInput.build(), BorderLayout.EAST);
    }

    @Override
    public JPanel build() {
	return questionContainer;
    }

    @Override
    public void dataUpdate(Environment context) {
	questionContainer.setVisible(context.getVisible(name));
	questionInput.updateWidget(context.getValue(name));

	// Update the view.
	QuestionWidget.this.questionContainer.revalidate();
	QuestionWidget.this.questionContainer.repaint();
    }

    @Override
    public void widgetUpdated(QLValue value) {
	viewListeners.forEach(l -> l.viewUpdate(name, value));
    }

    public void addUIListener(ViewListener listener) {
	viewListeners.add(listener);
    }

    @Override
    public void addWidgetListener(InputWidget listener) {
	widgetListeners.add(listener);
    }

}
