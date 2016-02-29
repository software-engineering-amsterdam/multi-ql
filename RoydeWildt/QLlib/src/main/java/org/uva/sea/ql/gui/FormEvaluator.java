package org.uva.sea.ql.gui;

import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.visitor.EvalVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 29-2-16.
 */
public class FormEvaluator extends EvalVisitor <Void, Void, Void> {
    private List<Question> questions;

    public FormEvaluator(Form f) {
        super(f);
        this.questions = new ArrayList<>();
        f.accept(this, null);
    }

    @Override
    public Void visit(Question stat, Void context) {
        questions.add(stat);
        return null;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
