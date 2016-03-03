package org.uva.sea.ql.gui;

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.EvalVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roy on 29-2-16.
 */
public class FormEvaluator extends EvalVisitor <Void, Void, Void> {
    private List<Question> questions;
    private ObservableMap<Var, Question> symbolTable;

    public FormEvaluator(Form f) {
        super(f, FXCollections.observableHashMap());
        symbolTable = FXCollections.observableHashMap();
        this.questions = new ArrayList<>();
        f.accept(this, null);
    }

    public FormEvaluator(Form f, ObservableMap<Var, Question> symbolTable) {
        super(f, symbolTable);
        this.symbolTable = symbolTable;
        this.questions = new ArrayList<>();
        f.accept(this, symbolTable);
    }

    @Override
    public Void visit(Question stat, Map<Var,Question> symbolTable) {
        Expr expr = stat.getExpr();

        Expr computedValue = expr.accept(this,symbolTable);

        Question computedQuestion = new Question(stat.getLine(),
                                        stat.getLabel(),
                                        stat.getVarname(),
                                        stat.getType(),
                                        computedValue);

        questions.add(computedQuestion);
        return null;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public ObservableMap<Var, Question> getSymbolTable() {
        return symbolTable;
    }
}
