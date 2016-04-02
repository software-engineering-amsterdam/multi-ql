package eu.bankersen.kevin.ql.gui.widgets;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.ViewListener;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public abstract class QuestionPane implements Widget {

	private final Question question;
	private final BorderPane widget;

	private final List<ViewListener> viewListeners;

	public QuestionPane(Question question) {
		this.question = question;
		this.widget = new BorderPane();
		this.viewListeners = new ArrayList<>();

		Text text = new Text(question.text());
		text.setWrappingWidth(275);

		widget.setPadding(new Insets(0, 10, 0, 0));
		widget.managedProperty().bind(widget.visibleProperty());
		widget.setLeft(text);
	}

	protected void setInput(Pane input) {
		widget.setRight(input);
	}

	public String name() {
		return question.name();
	}

	public boolean isComputed() {
		return question.isComputed();
	}

	protected void visible(boolean bool) {
		widget.setVisible(bool);
	}

	@Override
	public Pane draw() {
		return widget;
	}

	protected void sentData(String data) {
		Value value = question.type().value(data);
		viewListeners.forEach(listener -> listener.viewUpdate(name(), value));
	}

	@Override
	public void addViewListener(ViewListener listener) {
		viewListeners.add(listener);
	}

}
