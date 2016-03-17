package org.uva.sea.ql.evaluator;

import javafx.collections.ObservableMap;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.visitor.EvalVisitor;
import org.uva.sea.ql.evaluator.value.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by roy on 29-2-16.
 */
public class FormEvaluator extends EvalVisitor <Void, Void, Void> {
    private List<EvaluatedQuestion> questions;

    public FormEvaluator(Form f) {
        super(f);
        this.questions = new ArrayList<>();
        f.accept(this, this.getSymbolTable());
    }

    public FormEvaluator(Form f, ObservableMap<Var, Question> symbolTable) {
        super(f, symbolTable);
        this.questions = new ArrayList<>();
        f.accept(this, this.getSymbolTable());
    }

    @Override
    public Void visit(Question stat, Map<Var,Question> symbolTable) {

        Expr expr;
        if(symbolTable.containsKey(stat.getVarname())){
            expr = symbolTable.get(stat.getVarname()).getExpr();
        }
        else {
            expr = stat.getExpr();
        }

        Value computedValue = expr.accept(this,symbolTable);

        questions.add(new EvaluatedQuestion(
                                        stat.getLabel(),
                                        stat.getVarname(),
                                        stat.getType(),
                                        computedValue,
                                        stat.isComputed()));

        return null;
    }

    public List<EvaluatedQuestion> getQuestions() {
        return questions;
    }

}
