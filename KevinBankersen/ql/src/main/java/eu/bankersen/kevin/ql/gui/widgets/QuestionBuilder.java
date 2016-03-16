package eu.bankersen.kevin.ql.gui.widgets;

import java.util.LinkedList;
import java.util.List;

import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;

public class QuestionBuilder implements QuestionVisitor<Void> {

    private final List<QuestionWidget> questionWidgets;

    public QuestionBuilder(Form form) {
	this.questionWidgets = new LinkedList<>();
	form.accept(this, null);
    }

    public List<QuestionWidget> getWidgets() {
	return questionWidgets;
    }

    @Override
    public Void visit(Form o, Void empty) {
	o.body().accept(this, empty);
	return null;
    }

    @Override
    public Void visit(Body o, Void empty) {
	for (AbstractStatement s : o.statements()) {
	    s.accept(this, empty);
	}
	return null;
    }

    @Override
    public Void visit(IFStatement o, Void empty) {
	o.body().accept(this, empty);
	return null;
    }

    @Override
    public Void visit(ElseStatement o, Void empty) {
	o.body().accept(this, empty);
	o.elseBody().accept(this, empty);
	return null;
    }

    @Override
    public Void visit(NormalQuestion o, Void empty) {
	questionWidgets.add(new QuestionWidget(o.name(), o.text(), false, o.type()));
	return null;
    }

    @Override
    public Void visit(ComputedQuestion o, Void empty) {
	questionWidgets.add(new QuestionWidget(o.name(), o.text(), true, o.type()));
	return null;
    }
}
