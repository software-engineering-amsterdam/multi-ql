package eu.bankersen.kevin.ql.gui.widgets;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.form.ast.stat.Question;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.ViewListener;
import eu.bankersen.kevin.ql.interperter.DataListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public abstract class QuestionWidget implements DataListener {

    private final Question question;
    private final BorderPane widget;

    private final List<ViewListener> viewListeners;

    public QuestionWidget(Question question) {
	this.question = question;
	this.widget = new BorderPane();
	this.viewListeners = new ArrayList<>();

	Label text = new Label(question.text());
	text.setWrapText(true);
	text.setTextAlignment(TextAlignment.JUSTIFY);

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

    public void showWidget(boolean hide) {
	widget.setVisible(hide);
    }

    public Pane widget() {
	return widget;
    }

    protected void sentData(String data) {
	Value value = question.type().createQLValueFrom(data);
	viewListeners.forEach(listener -> listener.viewUpdate(name(), value));
    }

    public void addUIListener(ViewListener listener) {
	viewListeners.add(listener);
    }

}
