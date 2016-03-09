package org.uva.sea.ql.evaluator;

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.var.Var;
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
        f.accept(this, this.getSymbolTable());
    }

    public FormEvaluator(Form f, ObservableMap<Var, Question> symbolTable) {
        super(f, symbolTable);
        this.questions = new ArrayList<>();
        f.accept(this, this.getSymbolTable());
    }

    @Override
    public Void visit(Question stat, ObservableMap<Var,Question> symbolTable) {
        Expr expr;

        if(symbolTable.containsKey(stat.getVarname())){
            expr = symbolTable.get(stat.getVarname()).getExpr();
        }
        else {
            expr = stat.getExpr();
        }

        Expr computedValue = expr.accept(this,symbolTable);

        Question computedQuestion = new Question(stat.getLine(),
                                        stat.getLabel(),
                                        stat.getVarname(),
                                        stat.getType(),
                                        computedValue,
                                        stat.isComputed());

        questions.add(computedQuestion);
        return null;
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
