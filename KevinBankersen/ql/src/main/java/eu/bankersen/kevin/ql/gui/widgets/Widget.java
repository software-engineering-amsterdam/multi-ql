package eu.bankersen.kevin.ql.gui.widgets;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.form.interperter.DataListener;
import eu.bankersen.kevin.ql.gui.ViewListener;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public abstract class Widget extends BorderPane implements DataListener {

	private final Question question;
	private final List<ViewListener> viewListeners;

	public Widget(Question question) {
		this.question = question;
		this.viewListeners = new ArrayList<>();

		Text text = new Text(question.toString());
		text.setWrappingWidth(275);

		setPadding(new Insets(0, 10, 0, 0));
		managedProperty().bind(this.visibleProperty());
		setLeft(text);
	}

	public String name() {
		return question.name();
	}

	public boolean isComputed() {
		return question.isComputed();
	}

	protected void sendData(String data) {
		Value value = question.type().parse(data);
		viewListeners.forEach(listener -> listener.viewUpdate(name(), value));
	}

	public void addViewListener(ViewListener listener) {
		viewListeners.add(listener);
	}

}
