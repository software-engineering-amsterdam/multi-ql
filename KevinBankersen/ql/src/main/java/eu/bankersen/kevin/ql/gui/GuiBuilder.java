package eu.bankersen.kevin.ql.gui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eu.bankersen.kevin.ql.form.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;

public class GuiBuilder {

    private final List<QuestionWidget> questionWidgets;

    public GuiBuilder(Form form) {
	this.questionWidgets = new LinkedList<>();
	form.accept(new TopDownQuestionVisitor<Void>() {

	    @Override
	    public void visit(NormalQuestion question, Void context) {
		questionWidgets.add(question.type().defaultWidget(question));
	    }

	    @Override
	    public void visit(ComputedQuestion question, Void context) {
		questionWidgets.add(question.type().defaultWidget(question));
	    }
	}, null);
    }

    public Iterator<QuestionWidget> questionIterator() {
	return questionWidgets.iterator();
    }
}
