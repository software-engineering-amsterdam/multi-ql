package eu.bankersen.kevin.ql.gui;

import java.util.LinkedList;
import java.util.List;

import eu.bankersen.kevin.ql.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;

public class GuiBuilder {

    private final List<QuestionWidget> questionWidgets;

    public GuiBuilder(Form form) {
	this.questionWidgets = new LinkedList<>();
	form.accept(new TopDownQuestionVisitor<Void>() {

	    @Override
	    public void visit(NormalQuestion o, Void context) {
		questionWidgets.add(new QuestionWidget(o.name(), o.text(), false, o.type()));
	    }

	    @Override
	    public void visit(ComputedQuestion o, Void context) {
		questionWidgets.add(new QuestionWidget(o.name(), o.text(), true, o.type()));
	    }
	}, null);
    }

    public List<QuestionWidget> getWidgets() {
	return questionWidgets;
    }
}
