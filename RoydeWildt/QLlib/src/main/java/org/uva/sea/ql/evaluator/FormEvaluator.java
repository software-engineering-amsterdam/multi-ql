package org.uva.sea.ql.evaluator;

import javafx.collections.FXCollections;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class FormEvaluator<FORM,TYPE> extends BaseVisitor<FORM,Void,Value,TYPE,Value,Map<Var, Value>> {

    private final Map<Var,Value> symbolTable;
    private final List<EvaluatedQuestion> evaluatedQuestions;
    private final ExprEvaluator exprEvaluator;

    public FormEvaluator(Form f) {
        this.exprEvaluator = new ExprEvaluator();
        this.evaluatedQuestions = new ArrayList<>();
        this.symbolTable = new SymbolTable(f, FXCollections.observableHashMap()).getSymbolTable();
        f.accept(this, symbolTable);
    }

    public FormEvaluator(Form f, Map<Var, Value> symbolTable) {
        this.exprEvaluator = new ExprEvaluator();
        this.evaluatedQuestions = new ArrayList<>();
        this.symbolTable = new SymbolTable(f, symbolTable).getSymbolTable();
        f.accept(this, symbolTable);
    }

    @Override
    public Void visit(Question stat, Map<Var,Value> symbolTable) {

        Value value = symbolTable.get(stat.getVarname());

        evaluatedQuestions.add(new EvaluatedQuestion(
                stat.getLabel(),
                stat.getVarname(),
                stat.getType(),
                value,
                stat.isComputed()));

        return null;
    }


    @Override
    public Void visit(If stat, Map<Var, Value> symbolTable) {
        Bool bool = (Bool) stat.getCond().accept(exprEvaluator, symbolTable);

        if(bool.getValue() == null){
            return null;
        }

        if(bool.getValue()){
            for(Stat s : stat.getStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public Void visit(IfElse stat, Map<Var, Value> symbolTable) {
        Bool bool = (Bool) stat.getCond().accept(exprEvaluator, symbolTable);

        if(bool.getValue() == null){
            return null;
        }

        if(bool.getValue()){
            for(Stat s : stat.getIfStms())
                s.accept(this, symbolTable);
        }
        else {
            for(Stat s : stat.getElseStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    public List<EvaluatedQuestion> getEvaluatedQuestions() {
        return evaluatedQuestions;
    }

    public Map<Var, Value> getSymbolTable() {
        return symbolTable;
    }
}
