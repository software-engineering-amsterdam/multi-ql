package org.uva.sea.ql.gui;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.expr.unary.UnaryExpr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
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
        Expr expr = stat.getExpr();
        Expr computedValue = expr.accept(this,null);
        Question computedQuestion = stat;
        computedQuestion = new Question(stat.getLine(), stat.getLabel(), stat.getVarname(), stat.getType(), computedValue);

        questions.add(computedQuestion);
        return null;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
